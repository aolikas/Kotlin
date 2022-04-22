package my.aolika.a7minutesworkout.model

import my.aolika.a7minutesworkout.R

object Constants {

    fun defaultExerciseList(): ArrayList<ExerciseModel> {
        val exerciseList = ArrayList<ExerciseModel>()

        val jumpingJacks = ExerciseModel(
            1,
            "Jumping Jacks",
            R.drawable.jump,
            false,
            false
        )
        exerciseList.add(jumpingJacks)

        val lunge = ExerciseModel(
            2,
            "Lunge",
            R.drawable.lunge,
            false,
            false
        )
        exerciseList.add(lunge)

        val plank = ExerciseModel(
            3,
            "Plank",
            R.drawable.plank,
            false,
            false
        )
        exerciseList.add(plank)

        val pushUp = ExerciseModel(
            4,
            "Push Up",
            R.drawable.push_up,
            false,
            false
        )
        exerciseList.add(pushUp)

        val pushUpRotation = ExerciseModel(
            5,
            "Push Up and Rotation",
            R.drawable.push_up_rotation,
            false,
            false
        )
        exerciseList.add(pushUpRotation)

        val sidePlank = ExerciseModel(
            6,
            "Side Plank",
            R.drawable.side_plank,
            false,
            false
        )
        exerciseList.add(sidePlank)

        val squat = ExerciseModel(
            7,
            "Squat",
            R.drawable.squat,
            false,
            false
        )
        exerciseList.add(squat)


        val stepUp = ExerciseModel(
            8,
            "Step Up",
            R.drawable.step_up,
            false,
            false
        )
        exerciseList.add(stepUp)

        val triceps = ExerciseModel(
            9,
            "Triceps",
            R.drawable.triceps,
            false,
            false
        )
        exerciseList.add(triceps)

        val wallSit = ExerciseModel(
            10,
            "Wall Sit",
            R.drawable.wall_sit,
            false,
            false
        )
        exerciseList.add(wallSit)

        val abdominal = ExerciseModel(
            11,
            "Abdominal",
            R.drawable.abdominal,
            false,
            false
        )
        exerciseList.add(abdominal)

        val highKnees = ExerciseModel(
            12,
            "High Knees",
            R.drawable.high_kness,
            false,
            false
        )
        exerciseList.add(highKnees)

        return exerciseList

    }
}