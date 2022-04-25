package my.aolika.a7minutesworkout.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import my.aolika.a7minutesworkout.databinding.ItemExerciseBinding
import my.aolika.a7minutesworkout.model.ExerciseModel

class ExerciseRecyclerAdapter(val items: ArrayList<ExerciseModel>) :
    RecyclerView.Adapter<ExerciseRecyclerAdapter.ExerciseViewHolder>() {

    class ExerciseViewHolder(item: ItemExerciseBinding) : RecyclerView.ViewHolder(item.root) {
        val tvItem = item.tvItem

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        return ExerciseViewHolder(
            ItemExerciseBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val model: ExerciseModel = items[position]
        holder.tvItem.text = model.getId().toString()
    }

    override fun getItemCount(): Int {
        return items.size
    }
}