//тут типо будет сама логика игры и ломание на пазл//
package com.example.puzzlegame

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import kotlin.math.abs
import kotlin.random.Random

class PuzzleView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var image: Bitmap? = null
    private var pieces = mutableListOf<PuzzlePiece>()
    private var slots = mutableListOf<Rect>()
    private var selectedPiece: PuzzlePiece? = null
    private var difficulty = 3
    private var pieceWidth = 0
    private var pieceHeight = 0
    private var onCompleteListener: (() -> Unit)? = null

    fun setImageResource(resId: Int) {
        image = BitmapFactory.decodeResource(resources, resId)
        initializePuzzle()
    }

    fun setDifficulty(rows: Int) {
        difficulty = rows
        initializePuzzle()
    }

    fun setOnCompleteListener(listener: () -> Unit) {
        onCompleteListener = listener
    }

    fun restartPuzzle() {
        initializePuzzle()
    }

    private fun initializePuzzle() {
        pieces.clear()
        slots.clear()

        image?.let { _ -> 
            pieceWidth = width / difficulty
            pieceHeight = height / difficulty

            for (row in 0 until difficulty) {
                for (col in 0 until difficulty) {
                    // pystoii slot
                    val slot = Rect(col * pieceWidth, row * pieceHeight,
                        (col + 1) * pieceWidth, (row + 1) * pieceHeight)
                    slots.add(slot)

                    // random posistiia
                    val randomX = Random.nextInt(width - pieceWidth)
                    val randomY = Random.nextInt(height - pieceHeight)

                    val piece = PuzzlePiece(
                        row * difficulty + col,
                        row, col,
                        Rect(randomX, randomY, randomX + pieceWidth, randomY + pieceHeight),
                        slot
                    )
                    pieces.add(piece)
                }
            }
        }
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // detalki
        pieces.forEach { piece ->
            if (!piece.isPlacedCorrectly) {
                image?.let { bitmap ->
                    val srcRect = Rect(
                        piece.originalCol * pieceWidth,
                        piece.originalRow * pieceHeight,
                        (piece.originalCol + 1) * pieceWidth,
                        (piece.originalRow + 1) * pieceHeight
                    )

                    canvas.drawBitmap(bitmap, srcRect, piece.currentPosition, null)

                    // risyem
                    val borderPaint = Paint().apply {
                        color = if (piece == selectedPiece) Color.GREEN else Color.parseColor("#00ff00f0")
                        style = Paint.Style.STROKE
                        strokeWidth = 4f
                    }
                    canvas.drawRect(piece.currentPosition, borderPaint)
                }
            }
        }

        // sobrannost puzzla
        if (pieces.all { it.isPlacedCorrectly }) {
            onCompleteListener?.invoke()
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                selectedPiece = pieces.find { piece ->
                    !piece.isPlacedCorrectly && piece.currentPosition.contains(event.x.toInt(), event.y.toInt())
                }
            }

            MotionEvent.ACTION_MOVE -> {
                selectedPiece?.let { piece ->
                    piece.currentPosition.offsetTo(
                        (event.x - pieceWidth / 2).toInt(),
                        (event.y - pieceHeight / 2).toInt()
                    )
                    invalidate()
                }
            }

            MotionEvent.ACTION_UP -> {
                selectedPiece?.let { piece ->
                    val targetSlot = slots[piece.id]
                    if (abs(piece.currentPosition.centerX() - targetSlot.centerX()) < pieceWidth / 2 &&
                        abs(piece.currentPosition.centerY() - targetSlot.centerY()) < pieceHeight / 2) {

                        piece.currentPosition.set(targetSlot)
                        piece.isPlacedCorrectly = true
                    }
                    invalidate()
                }
                selectedPiece = null
            }
        }
        return true
    }

    data class PuzzlePiece(
        val id: Int,
        val originalRow: Int,
        val originalCol: Int,
        var currentPosition: Rect,
        val correctPosition: Rect
    ) {
        var isPlacedCorrectly = false
    }
}
