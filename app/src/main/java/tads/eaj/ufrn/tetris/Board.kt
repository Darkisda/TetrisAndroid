package tads.eaj.ufrn.tetris

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import tads.eaj.ufrn.tetris.databinding.BoardBinding
import tads.eaj.ufrn.tetris.modelView.BoardViewModel
import tads.eaj.ufrn.tetris.models.*
import tads.eaj.ufrn.tetris.utils.constants.Constants
import tads.eaj.ufrn.tetris.utils.constants.Difficulties

class Board : AppCompatActivity() {
    private val LINE = 36
    private val COL = 26
    private var running = true
    private var speed: Long = 300

    lateinit var binding: BoardBinding
    lateinit var viewModel: BoardViewModel

    lateinit var game: Game

    var block: Block = randBlock()

    var boardView = Array(LINE) {
        arrayOfNulls<ImageView>(COL)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.board)
        viewModel = ViewModelProvider(this).get(BoardViewModel::class.java)

        var settings = getSharedPreferences(Constants.PREFS, Context.MODE_PRIVATE)

        val inflater = LayoutInflater.from(this)

        binding.gridBoardLayout.rowCount = LINE
        binding.gridBoardLayout.columnCount = COL

        for (i in 0 until LINE) {
            for (j in 0 until COL) {
                boardView[i][j] = inflater.inflate(
                    R.layout.inflate_image_view,
                    binding.gridBoardLayout,
                    false
                ) as ImageView
                binding.gridBoardLayout.addView(boardView[i][j])
            }
        }

        binding.apply {
            val params = intent.extras
            val boardParams = params!!.getString(Constants.BOARD)

            var difficulty = settings.getString(Difficulties.DIFFICULTY, Difficulties.EASY)

            when (difficulty!!) {
                Difficulties.EASY -> speed = 500
                Difficulties.MEDIUM -> speed = 300
                Difficulties.HARD -> speed = 100
            }

            pauseButton.setOnClickListener {
                if(running) {
                    running = false
                } else {
                    running = true
                    gameRun()
                }
            }

            leftButton.setOnClickListener {
                if(!running ||
                        block.piece1.y == 0 ||
                        block.piece2.y == 0 ||
                        block.piece3.y == 0 ||
                        block.piece4.y == 0 ||
                        viewModel.board[block.piece1.x][block.piece1.y - 1] == 1 ||
                        viewModel.board[block.piece2.x][block.piece2.y - 1] == 1 ||
                        viewModel.board[block.piece3.x][block.piece3.y - 1] == 1 ||
                        viewModel.board[block.piece4.x][block.piece4.y - 1] == 1) {
                    return@setOnClickListener
                }
                block.moveLeft()
            }

            rightButton.setOnClickListener {
                if(!running ||
                        block.piece1.y + 1 == COL ||
                        block.piece2.y + 1 == COL ||
                        block.piece3.y + 1 == COL ||
                        block.piece4.y + 1 == COL ||
                        viewModel.board[block.piece1.x][block.piece1.y + 1] == 1 ||
                        viewModel.board[block.piece2.x][block.piece2.y + 1] == 1 ||
                        viewModel.board[block.piece3.x][block.piece3.y + 1] == 1 ||
                        viewModel.board[block.piece4.x][block.piece4.y + 1] == 1) {
                    return@setOnClickListener
                }
                block.moveRight()
            }

            game = Game(difficulty!!)
        }
        gameRun()
    }

    private fun gameRun() {
        Thread {
            while (running) {
                Thread.sleep(speed)
                runOnUiThread {
                    for (i in 0 until LINE) {
                        for ( j in 0 until  COL) {
                            if (viewModel.board[i][j] == 1) {
                                boardView[i][j]!!.setImageResource(R.drawable.white)
                            } else {
                                boardView[i][j]!!.setImageResource(R.drawable.black)
                            }
                        }
                    }
                    if (canMoveDown()) {
                        block.moveDown()
                    } else {
                        checkPoints()
                    }

                    try {
                        boardView[block.piece1.x][block.piece1.y]!!.setImageResource(R.drawable.white)
                        boardView[block.piece2.x][block.piece2.y]!!.setImageResource(R.drawable.white)
                        boardView[block.piece3.x][block.piece3.y]!!.setImageResource(R.drawable.white)
                        boardView[block.piece4.x][block.piece4.y]!!.setImageResource(R.drawable.white)
                    } catch (e: ArrayIndexOutOfBoundsException) {
                        running = false
                    }
                }
            }
        }.start()
    }

    private fun randBlock(): Block {
        return when ((1..8).random()) {
            1 -> Block_I(5,15)
            2 -> Block_L(5,15)
            3 -> Block_L_Invert(5,15)
            4 -> Block_S(5,15)
            5 -> Block_S_Invert(5,15)
            6 -> Block_T(5,15)
            7 -> Block_T_Invert(5,15)
            else -> Block_square(5,15)
        }
    }

    override fun onStop() {
        running = false
        super.onStop()
    }

    private fun checkPoints() {
        viewModel.board[block.piece1.x][block.piece1.y] = 1
        viewModel.board[block.piece2.x][block.piece2.y] = 1
        viewModel.board[block.piece3.x][block.piece3.y] = 1
        viewModel.board[block.piece4.x][block.piece4.y] = 1

        for(i in 0 until LINE) {
            if(viewModel.board[i].sum() == COL) {
                viewModel.points += 25
                binding.points.text = viewModel.points.toString()

                for(j in i downTo 1) {
                    viewModel.board[j] = viewModel.board[j-1]
                }
            }
        }
        block = randBlock()
    }

    private fun canMoveDown(): Boolean {
        return (
                block.piece1.x + 1 != LINE &&
                        block.piece2.x + 1 != LINE &&
                        block.piece3.x + 1 != LINE &&
                        block.piece4.x + 1 != LINE &&
                        viewModel.board[block.piece1.x + 1][block.piece1.y] == 0 &&
                        viewModel.board[block.piece2.x + 1][block.piece2.y] == 0 &&
                        viewModel.board[block.piece3.x + 1][block.piece3.y] == 0 &&
                        viewModel.board[block.piece4.x + 1][block.piece4.y] == 0
                )
    }
}