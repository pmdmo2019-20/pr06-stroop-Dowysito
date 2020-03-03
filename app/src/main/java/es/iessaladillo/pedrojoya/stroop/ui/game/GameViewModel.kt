package es.iessaladillo.pedrojoya.stroop.ui.game

import android.app.Application
import android.graphics.Color
import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.stroop.R
import es.iessaladillo.pedrojoya.stroop.data.Game
import es.iessaladillo.pedrojoya.stroop.data.Player
import es.iessaladillo.pedrojoya.stroop.data.Repository
import kotlin.concurrent.thread


class GameViewModel(private val repository: Repository,
                    private val application: Application) : ViewModel() {


    @Volatile
    private var isGameFinished: Boolean = false
    @Volatile
    private var currentWordMillis: Int = 0
    @Volatile
    private var millisUntilFinished: Int = 0
    private val handler: Handler = Handler()

    var max: Int = 25
    var gamemode:String= "Time"
    var progress: MutableLiveData<Int> = MutableLiveData(max)

    lateinit var player: Player
    var currentGame: Game? =null
    private val text: List<String> = listOf("Red","Green","Blue","Yellow")
    private val color: List<Int> = listOf(Color.RED,Color.GREEN,Color.BLUE,Color.YELLOW)
    var words: MutableLiveData<Int> = MutableLiveData(0)
    var correct: MutableLiveData<Int> = MutableLiveData(0)
    var points: MutableLiveData<Int> = MutableLiveData(0)
    var attempts: MutableLiveData<Int> = MutableLiveData(5)
    var incorrect: Int = 0
    var colordata: MutableLiveData<Int> = MutableLiveData(color.shuffled()[0])
    var txt: MutableLiveData<String> = MutableLiveData(text.shuffled()[0])

    private fun onGameTimeTick(millisUntilFinished: Int) {
        progress.value=millisUntilFinished
    }

    fun onGameTimeFinish() {
        isGameFinished = true
        currentGame = Game(0,gamemode,max, words.value!!,correct.value!!,points.value!!,incorrect,player.id)
        Thread{
            repository.insertGame(currentGame!!)
        }.start()
    }

    fun nextWord() {
        words.value= words.value!!.plus(1)
        colordata.value= color.shuffled()[0]
        txt.value= text.shuffled()[0]
    }

    fun checkRight() {
        currentWordMillis = 0
        if (gamemode.equals("Time")){
            when (txt.value){
                "Red" -> {
                    if (colordata.value==Color.RED){
                        points.value= points.value!!.plus(10)
                        correct.value= correct.value!!.plus(1)
                        nextWord()
                    }else{
                        incorrect++
                        nextWord()
                    }
                }
                "Green" -> {
                    if (colordata.value==Color.GREEN){
                        points.value= points.value!!.plus(10)
                        correct.value= correct.value!!.plus(1)
                        nextWord()
                    }else{
                        incorrect++
                        nextWord()
                    }
                }
                "Blue" -> {
                    if (colordata.value==Color.BLUE){
                        points.value= points.value!!.plus(10)
                        correct.value= correct.value!!.plus(1)
                        nextWord()
                    }else{
                        incorrect++
                        nextWord()
                    }
                }
                "Yellow" -> {
                    if (colordata.value==Color.YELLOW){
                        points.value= points.value!!.plus(10)
                        correct.value= correct.value!!.plus(1)
                        nextWord()
                    }else{
                        incorrect++
                        nextWord()
                    }
                }
            }
        }
        else{
            when (txt.value){
                "Red" -> {
                    if (colordata.value==Color.RED){
                        points.value= points.value!!.plus(10)
                        correct.value= correct.value!!.plus(1)
                        nextWord()
                    }else{
                        incorrect++
                        if(attempts.value!!.minus(1)==0){
                            attempts.value= attempts.value!!.minus(1)
                            onGameTimeFinish()
                        }else{
                            attempts.value= attempts.value!!.minus(1)
                            nextWord()
                        }

                    }
                }
                "Green" -> {
                    if (colordata.value==Color.GREEN){
                        points.value= points.value!!.plus(10)
                        correct.value= correct.value!!.plus(1)
                        nextWord()
                    }else{
                        incorrect++
                        if(attempts.value!!.minus(1)==0){
                            attempts.value= attempts.value!!.minus(1)
                            onGameTimeFinish()
                        }else{
                            attempts.value= attempts.value!!.minus(1)
                            nextWord()
                        }
                    }
                }
                "Blue" -> {
                    if (colordata.value==Color.BLUE){
                        points.value= points.value!!.plus(10)
                        correct.value= correct.value!!.plus(1)
                        nextWord()
                    }else{
                        incorrect++
                        if(attempts.value!!.minus(1)==0){
                            attempts.value= attempts.value!!.minus(1)
                            onGameTimeFinish()
                        }else{
                            attempts.value= attempts.value!!.minus(1)
                            nextWord()
                        }
                    }
                }
                "Yellow" -> {
                    if (colordata.value==Color.YELLOW){
                        points.value= points.value!!.plus(10)
                        correct.value= correct.value!!.plus(1)
                        nextWord()
                    }else{
                        incorrect++
                        if(attempts.value!!.minus(1)==0){
                            attempts.value= attempts.value!!.minus(1)
                            onGameTimeFinish()
                        }else{
                            attempts.value= attempts.value!!.minus(1)
                            nextWord()
                        }
                    }
                }
            }
        }
    }

    fun checkWrong() {
        currentWordMillis = 0
        if (gamemode.equals("Time")){
            when (txt.value){
                "Red" -> {
                    if (colordata.value!=Color.RED){
                        points.value= points.value!!.plus(10)
                        correct.value= correct.value!!.plus(1)
                        nextWord()
                    }else{
                        incorrect++
                        nextWord()
                    }
                }
                "Green" -> {
                    if (colordata.value!=Color.GREEN){
                        points.value= points.value!!.plus(10)
                        correct.value= correct.value!!.plus(1)
                        nextWord()
                    }else{
                        incorrect++
                        nextWord()
                    }
                }
                "Blue" -> {
                    if (colordata.value!=Color.BLUE){
                        points.value= points.value!!.plus(10)
                        correct.value= correct.value!!.plus(1)
                        nextWord()
                    }else{
                        incorrect++
                        nextWord()
                    }
                }
                "Yellow" -> {
                    if (colordata.value!=Color.YELLOW){
                        points.value= points.value!!.plus(10)
                        correct.value= correct.value!!.plus(1)
                        nextWord()
                    }else{
                        incorrect++
                        nextWord()
                    }
                }
            }
        }
        else{
            when (txt.value){
                "Red" -> {
                    if (colordata.value!=Color.RED){
                        points.value= points.value!!.plus(10)
                        correct.value= correct.value!!.plus(1)
                        nextWord()
                    }else{
                        incorrect++
                        if(attempts.value!!.minus(1)==0){
                            attempts.value= attempts.value!!.minus(1)
                            onGameTimeFinish()
                        }else{
                            attempts.value= attempts.value!!.minus(1)
                            nextWord()
                        }
                    }
                }
                "Green" -> {
                    if (colordata.value!=Color.GREEN){
                        points.value= points.value!!.plus(10)
                        correct.value= correct.value!!.plus(1)
                        nextWord()
                    }else{
                        incorrect++
                        if(attempts.value!!.minus(1)==0){
                            attempts.value= attempts.value!!.minus(1)
                            onGameTimeFinish()
                        }else{
                            attempts.value= attempts.value!!.minus(1)
                            nextWord()
                        }
                    }
                }
                "Blue" -> {
                    if (colordata.value!=Color.BLUE){
                        points.value= points.value!!.plus(10)
                        correct.value= correct.value!!.plus(1)
                        nextWord()
                    }else{
                        incorrect++
                        if(attempts.value!!.minus(1)==0){
                            attempts.value= attempts.value!!.minus(1)
                            onGameTimeFinish()
                        }else{
                            attempts.value= attempts.value!!.minus(1)
                            nextWord()
                        }
                    }
                }
                "Yellow" -> {
                    if (colordata.value!=Color.YELLOW){
                        points.value= points.value!!.plus(10)
                        correct.value= correct.value!!.plus(1)
                        nextWord()
                    }else{
                        incorrect++
                        if(attempts.value!!.minus(1)==0){
                            attempts.value= attempts.value!!.minus(1)
                            onGameTimeFinish()
                        }else{
                            attempts.value= attempts.value!!.minus(1)
                            nextWord()
                        }
                    }
                }
            }
        }
    }

    fun startGameThread(gameTime: Int, wordTime: Int) {
        millisUntilFinished = gameTime
        currentWordMillis = 0
        isGameFinished = false
        val checkTimeMillis: Int = wordTime / 2
        thread {
            try {
                while (!isGameFinished) {
                    Thread.sleep(checkTimeMillis.toLong())
                    // Check and publish on main thread.
                    handler.post {
                        if (!isGameFinished) {
                            if (currentWordMillis >= wordTime) {
                                currentWordMillis = 0
                                nextWord()
                            }
                            currentWordMillis += checkTimeMillis
                            millisUntilFinished -= checkTimeMillis
                            onGameTimeTick(millisUntilFinished)
                            if (millisUntilFinished <= 0) {
                                onGameTimeFinish()
                            }
                        }
                    }
                }
            } catch (ignored: Exception) {
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        isGameFinished = true
    }

}