package com.example.swipequiz

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {

    private val questions = ArrayList<Question>()
    private val questionAdapter = QuestionAdapter(questions)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)



        initViews()

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }


    private fun initQuestions() {

        val LIST_OF_QUESTIONS = arrayOf(
            Question("Carrots are orange"),
            Question("Humans cannot breathe underwater"),
            Question("Dogs can also be pink colored"),
            Question("You have to swipe left if a question is true")
        )
        for (i in LIST_OF_QUESTIONS) {
            questions.add(i)
        }
    }
    private fun initViews() {

        rvView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rvView.adapter = questionAdapter
        rvView.addItemDecoration(
            DividerItemDecoration(
                this@MainActivity,
                DividerItemDecoration.VERTICAL
            )
        )
        createItemTouchHelper().attachToRecyclerView(rvView)
        initQuestions()
        questionAdapter.notifyDataSetChanged()
    }

    fun onswipeRight(swipedQuestion: String, position: Int) {
        val swipeRight = ArrayList<Question>()
        swipeRight.add(Question("Carrots are orange"))
        swipeRight.add(Question("Humans cannot breathe underwater"))

        for (question in swipeRight) {
            if (swipedQuestion == question.questionText) {
                Snackbar.make(toolbar, "Correct!", Snackbar.LENGTH_SHORT).show()
                questions.removeAt(position)
                return
            }
        }
        Snackbar.make(toolbar, "incorrect!", Snackbar.LENGTH_SHORT).show()
    }

    fun onSwipeLeft(swipedQuestion: String, position: Int) {
        val swipeLeft = ArrayList<Question>()
        swipeLeft.add(Question("Dogs can also be pink colored"))
        swipeLeft.add(Question("You have to swipe left if a question is true"))

        for (question in swipeLeft) {
            if (swipedQuestion == question.questionText) {
                Snackbar.make(toolbar, "Correct!", Snackbar.LENGTH_SHORT).show()
                questions.removeAt(position)
                return
            }
        }
        Snackbar.make(toolbar, "incorrect!", Snackbar.LENGTH_SHORT).show()
    }




    private fun createItemTouchHelper(): ItemTouchHelper {

        val callback = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

            // Enables or Disables the ability to move items up and down.
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }



            // Callback triggered when a user swiped an item.
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                var swipedQuestion = questions.elementAt(position).questionText
                when {
                    direction > 5 -> onswipeRight(swipedQuestion, position)
                    direction < 5 -> onSwipeLeft(swipedQuestion, position)
                    else -> Snackbar.make(toolbar, "incorrect!", Snackbar.LENGTH_SHORT).show()
                }
                questionAdapter.notifyDataSetChanged()
            }

            // Callback triggered when a user swiped an item.
             fun onClick(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                var clickedQuestion = questions.elementAt(position).questionText
                Snackbar.make(toolbar, clickedQuestion, Snackbar.LENGTH_SHORT).show()
            }
        }
        return ItemTouchHelper(callback)
    }
}


