package luna.joel.digimind.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.taskview.view.*
import luna.joel.digimind.R
import luna.joel.digimind.Task

class HomeFragment : Fragment() {

    private var adaptador: AdaptadorTareas?=null

    companion object{
        var tasks=ArrayList<Task>()
        var first=true
    }
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)


        if(first){
            fillTasks()
            first=false
        }

        adaptador=AdaptadorTareas(root.context,tasks)
        root.gridview.adapter=adaptador

        return root
    }
    fun fillTasks(){
        tasks.add(Task("Practice 1", arrayListOf("Tuesday"),"17:30"))
        tasks.add(Task("Practice 2", arrayListOf("Monday","Sunday"),"17:00"))
        tasks.add(Task("Practice 3", arrayListOf("Wednesday"),"14:00"))
        tasks.add(Task("Practice 4", arrayListOf("Saturday"),"11:00"))
        tasks.add(Task("Practice 5", arrayListOf("Friday"),"13:00"))
        tasks.add(Task("Practice 6", arrayListOf("Thursday"),"10:40"))
        tasks.add(Task("Practice 7", arrayListOf("Monday"),"12:00"))
    }

    private class AdaptadorTareas: BaseAdapter {

        var tasks=ArrayList<Task>()
        var contexto: Context?=null

        constructor(contexto: Context, tasks:ArrayList<Task>){
            this.contexto=contexto
            this.tasks=tasks
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var task=tasks[position]
            var inflador=LayoutInflater.from(contexto)
            var vista=inflador.inflate(R.layout.taskview,null)

            vista.tv_title.setText(task.title)
            vista.tv_time.setText(task.time)
            vista.tv_days.setText(task.days.toString())

            return vista
        }

        override fun getItem(position: Int): Any {
            return tasks[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return tasks.size
        }
    }

}
