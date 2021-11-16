package my.aolika.workoutapp

object Constants {

    fun defaultExercise(): ArrayList<ExerciseModel> {
        val exerciseList = ArrayList<ExerciseModel>()

        val jumpingJacks = ExerciseModel(
            1,
            "Jumping Jacks",
            R.drawable.jump,
            false,
            false
        )

        exerciseList.add(jumpingJacks)

        val wallSits = ExerciseModel(
            2,
            "Wall Sit",
            R.drawable.wall_sit,
            false,
            false
        )

        exerciseList.add(wallSits)

        val pushUp = ExerciseModel(
            3,
            "Push Up",
            R.drawable.push_up,
            false,
            false
        )

        exerciseList.add(pushUp)


        val abdominalCrunch = ExerciseModel(
            4,
            "AbdominalCrunch",
            R.drawable.abdominal,
            false,
            false
        )

        exerciseList.add(abdominalCrunch)

        val squats = ExerciseModel(
            5,
            "Squats",
            R.drawable.squat,
            false,
            false
        )

        exerciseList.add(squats)

        val stepUpOnChair = ExerciseModel(
            6,
            "Step Up On Chair",
            R.drawable.step_up,
            false,
            false
        )

        exerciseList.add(stepUpOnChair)


        val tricepsDipOnChair = ExerciseModel(
            7,
            "Triceps Dip On Chair",
            R.drawable.triceps,
            false,
            false
        )

        exerciseList.add(tricepsDipOnChair)

        val plank = ExerciseModel(
            8,
            "Plank",
            R.drawable.plank,
            false,
            false
        )

        exerciseList.add(plank)

        val highKneesRunningInPlace = ExerciseModel(
            9,
            "High Knees Running In Place",
            R.drawable.high_kness,
            false,
            false
        )

        exerciseList.add(highKneesRunningInPlace)

        val lunges = ExerciseModel(
            10,
            "Lunges",
            R.drawable.lunge,
            false,
            false
        )

        exerciseList.add(lunges)

        val pushUpAndRotation = ExerciseModel(
            11,
            "Push Up and Rotation",
            R.drawable.push_up_rotation,
            false,
            false
        )

        exerciseList.add(pushUpAndRotation)


        val sidePlank = ExerciseModel(
            12,
            "Side Plank",
            R.drawable.side_plank,
            false,
            false
        )

        exerciseList.add(sidePlank)

        return exerciseList
    }
}