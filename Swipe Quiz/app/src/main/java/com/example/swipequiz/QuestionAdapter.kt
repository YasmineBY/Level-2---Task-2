package com.example.swipequiz

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.item_question.view.*


class  QuestionAdapter(private val questions: List<Question>) :  RecyclerView.Adapter<QuestionAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_question, parent, false)
        )

    }

    override fun getItemCount() = questions.size

    override fun onBindViewHolder(holder: QuestionAdapter.ViewHolder, position: Int) {
        holder.bind(questions[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun bind(question: Question) {
            itemView.setOnClickListener{
                var swipedDirection = "temp"
                if(question.questionText == "Carrots are orange" || question.questionText =="A pyramid is shaped like a triangle" ){
                    swipedDirection = "The correct answer is: Swipe to right"
                } else {
                swipedDirection = "The correct answer is: Swipe to left"
                }



                Snackbar.make(itemView, swipedDirection , Snackbar.LENGTH_SHORT).show()

            }
//            itemView.tvPlace.text = place.name
//            itemView.ivPlace.setImageResource(place.imageResId)
            itemView.fldQuestion.text = question.questionText

        }

    }

}
