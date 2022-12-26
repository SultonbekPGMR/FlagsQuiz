package uz.codial.flagsquiz

import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.forEach
import uz.codial.flagsquiz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var answerButtonList = ArrayList<Button>()
    private var variantButtonList = ArrayList<Button>()
    private var questions = ArrayList<QuestionData>()
    private var typedAnswer = ArrayList<String>()
    private var typedVariants = ArrayList<Boolean>()
    private var score = 0
    private var countVisibleAnswerButton = 0

    private var level = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        questions = Constraints.loadData()
        questions.shuffle()
        initQuestion()


    }

    private fun initViews() {
        answerButtonList.addAll(
            getAllButtonByGroup(
                0,
                binding.containerAnswer.id,
                this::onclickAnswer
            )
        )
        variantButtonList.addAll(
            getAllButtonByGroup(
                0,
                binding.containerVariant1.id,
                this::onclickVariant
            )
        )
        variantButtonList.addAll(
            getAllButtonByGroup(
                variantButtonList.size,
                binding.containerVariant2.id,
                this::onclickVariant
            )
        )
    }


    private fun getAllButtonByGroup(
        startIndex: Int,
        viewGroupId: Int,
        onClickListener: View.OnClickListener
    ): ArrayList<Button> {
        val viewGroup: ViewGroup = findViewById(viewGroupId)
        val buttons = ArrayList<Button>(viewGroup.childCount)

        for (i in 0 until viewGroup.childCount) {
            val btn = viewGroup.getChildAt(i) as Button
            btn.setOnClickListener(onClickListener)
            btn.tag = i + startIndex
            buttons.add(btn)


        }

        return buttons

    }

    private fun onclickAnswer(view: View) {


        val index = view.tag as Int
        val text = typedAnswer[index]
        val questionData = getQuestion()
        val variant = questionData.variant

        for (i in 0 until variantButtonList.size) {
            val textVariant = variant[i].toString()

            if (!typedVariants[i] && textVariant == text) {
                answerButtonList[index].text = ""
                val animation = AnimationUtils.loadAnimation(this, R.anim.btn_answer_disappear)
                variantButtonList[i].startAnimation(animation)
                variantButtonList[i].visibility = View.VISIBLE

                typedAnswer[index] = "null"
                typedVariants[i] = true
                return

            }


        }

    }

    private fun onclickVariant(view: View) {

        val animation = AnimationUtils.loadAnimation(this, R.anim.btn_disappear)
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {
                binding.containerVariant1.forEach {
                    it.isClickable =false
                }
                binding.containerVariant2.forEach {
                    it.isClickable =false
                }
            }

            override fun onAnimationEnd(p0: Animation?) {
                val index = view.tag as Int
                val indexAnswer = typedAnswer.indexOf("null")
                if (indexAnswer == -1) return

                val questionData = getQuestion()
                val variant = questionData.variant
                val text = variant[index].toString()
                answerButtonList[indexAnswer].text = text
                answerButtonList[indexAnswer].setTextColor(Color.parseColor("#FFFFFFFF"))

                view.visibility = View.INVISIBLE
                typedAnswer[indexAnswer] = text
                typedVariants[index] = false
                checkWinner()
                binding.containerVariant1.forEach {
                    it.isClickable =true
                }
                binding.containerVariant2.forEach {
                    it.isClickable =true
                }

            }

            override fun onAnimationRepeat(p0: Animation?) {


            }

        })
        view.startAnimation(animation)


    }

    private fun checkWinner() {
        val questionData = getQuestion()
        val answer = questionData.answer
        val typedAnswerLetter = StringBuilder()

        for (i in 0 until typedAnswer.size) {
            typedAnswerLetter.append(typedAnswer[i])
        }

        val userAnswer = typedAnswerLetter.toString()
        if (userAnswer.length != answer.length) return

        if (userAnswer == answer) {
            Toast.makeText(this, "Correct Answer", Toast.LENGTH_SHORT).show()
            score++
            binding.tvScore.text = "score $score"

            answerButtonList.forEach {
                it.setTextColor(Color.parseColor("#00B633"))
                it.setBackgroundResource(R.drawable.correct_ans_back)
            }

            level++

            if (level == questions.size) {
                Toast.makeText(this, "Game Over", Toast.LENGTH_SHORT).show()
                level = 0
                questions.shuffle()
                initQuestion()
                binding.tvLevel.text = "level 1"
                binding.tvScore.text = "score 0"


            } else {
                val timer = object : CountDownTimer(1000, 1000) {
                    override fun onTick(millisUntilFinished: Long) {

                    }

                    override fun onFinish() {
                        initQuestion()
                        binding.tvLevel.text = "level ${level + 1}"

                    }
                }
                timer.start()
            }
        } else {
            Toast.makeText(this, "Wrong Answer", Toast.LENGTH_SHORT).show()
            if (score > 0) {
                score--
                binding.tvScore.text = "score $score"
            }
            val timer = object : CountDownTimer(1000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    answerButtonList.forEach {
                        it.setBackgroundResource(R.drawable.wrong_asn_back)
                        it.setTextColor(Color.parseColor("#FF0600"))

                    }
                }

                override fun onFinish() {
                    // clear button letters
                    answerButtonList.forEach {
                        for (i in 0 until countVisibleAnswerButton) {
                            onclickAnswer(answerButtonList[i] as View)
                        }
                        it.setBackgroundResource(R.drawable.btn_answ_back)
                    }
                }
            }
            timer.start()
        }


    }


    private fun getQuestion(): QuestionData {
        return questions[level]
    }

    private fun initQuestion() {
        countVisibleAnswerButton = 0
        answerButtonList.forEach {
            it.setBackgroundResource(R.drawable.btn_answ_back)
        }
        val questionData = getQuestion()
        val answer = questionData.answer
        val variant = questionData.variant
        binding.imageQuestion.setImageResource(questionData.image)
        typedAnswer.clear()
        for (i in 0 until answerButtonList.size) {
            val state = i < answer.length
            val btn = answerButtonList[i]
            btn.text = ""
            if (state) {
                btn.visibility = View.VISIBLE
                countVisibleAnswerButton++
                typedAnswer.add("null")
            } else {
                btn.visibility = View.GONE
            }
        }

        for (i in 0 until variantButtonList.size) {
            val btn = variantButtonList[i]
            btn.text = variant[i].toString()
            btn.visibility = View.VISIBLE
            typedVariants.add(true)
        }

    }


}