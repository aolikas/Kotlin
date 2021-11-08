package my.e.quizapp

import java.util.ArrayList

object Constants {
    const val USER_NAME: String = "user_name"
    const val TOTAL_QUESTION: String = "total_question"
    const val CORRECT_ANSWER: String = "correct_answer"

    fun getQuestions(): ArrayList<Question> {
        val questionList = ArrayList<Question>()
        val que1 = Question(
            1, "What country does this flag belong to?",
            R.drawable.ic_flag_of_argentina,
            "Argentina", "Australia",
            "Russia", "Armenia",
            1
        )
        questionList.add(que1)

        val que2 = Question(
            1, "What country does this flag belong to?",
            R.drawable.ic_flag_of_australia,
            "Fiji", "Australia",
            "New Zealand", "Brazil",
            2
        )

        questionList.add(que2)

        val que3 = Question(
            1, "What country does this flag belong to?",
            R.drawable.ic_flag_of_belgium,
            "Australia", "Denmark",
            "Germany", "Belgium",
            4
        )

        questionList.add(que3)

        val que4 = Question(
            1, "What country does this flag belong to?",
            R.drawable.ic_flag_of_brazil,
            "Kuwait", "India",
            "Chili", "Brazil",
            4
        )

        questionList.add(que4)

        val que5 = Question(
            1, "What country does this flag belong to?",
            R.drawable.ic_flag_of_new_zealand,
            "England", "Australia",
            "New Zealand", "Fiji",
            3
        )

        questionList.add(que5)

        val que6 = Question(
            1, "What country does this flag belong to?",
            R.drawable.ic_flag_of_kuwait,
            "Oman", "Turkey",
            "Kuwait", "India",
            3
        )

        questionList.add(que6)

        val que7 = Question(
            1, "What country does this flag belong to?",
            R.drawable.ic_flag_of_denmark,
            "Denmark", "Finland",
            "Norway", "Belgium",
            1
        )

        questionList.add(que7)

        val que8 = Question(
            1, "What country does this flag belong to?",
            R.drawable.ic_flag_of_fiji,
            "Kuwait", "Fiji",
            "New Zealand", "Brazil",
            2
        )

        questionList.add(que8)

        val que9 = Question(
            1, "What country does this flag belong to?",
            R.drawable.ic_flag_of_germany,
            "France", "Sweden",
            "Hungary", "Germany",
            4
        )

        questionList.add(que9)

        return questionList
    }
}