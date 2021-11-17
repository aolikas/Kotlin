package my.aolika.workoutapp

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import my.aolika.workoutapp.databinding.ItemExerciseStatusBinding

class ExerciseStatusAdapter(val exercisesList: ArrayList<ExerciseModel>) :
    RecyclerView.Adapter<ExerciseStatusAdapter.MyViewHolder>() {

    inner class MyViewHolder(private val itemBinding: ItemExerciseStatusBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        val tvItem = itemBinding.tvItemExercise
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemExerciseStatusBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val model: ExerciseModel = exercisesList[position]
        holder.tvItem.text = model.getId().toString()

        when {
            model.getIsSelected() -> {
                holder.tvItem.background = ContextCompat.getDrawable(
                    holder.itemView.context,
                    R.drawable.circular_color_selected_background
                )
                holder.tvItem.setTextColor(Color.parseColor("#212121"))
            }
            model.getIsCompleted() -> {
                holder.tvItem.background = ContextCompat.getDrawable(
                    holder.itemView.context,
                    R.drawable.cicrular_color_accent_background
                )
                holder.tvItem.setTextColor(Color.parseColor("#212121"))

            }
            else -> {
                holder.tvItem.background = ContextCompat.getDrawable(
                    holder.itemView.context,
                    R.drawable.circular_color_gray_background
                )
                holder.tvItem.setTextColor(Color.parseColor("#212121"))
            }
        }

    }

    override fun getItemCount(): Int {
        return exercisesList.size
    }
}