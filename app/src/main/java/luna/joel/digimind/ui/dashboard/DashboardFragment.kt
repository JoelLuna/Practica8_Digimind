package luna.joel.digimind.ui.dashboard

import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.fragment_dashboard.view.*
import luna.joel.digimind.R
import luna.joel.digimind.Task
import luna.joel.digimind.ui.home.HomeFragment
import java.text.SimpleDateFormat
import java.util.*

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        dashboardViewModel = ViewModelProviders.of(this).get(DashboardViewModel::class.java)

        root.btn_tiempo.setOnClickListener{
            val cal= Calendar.getInstance()
            val timeSetListener= TimePickerDialog.OnTimeSetListener{ timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY,hour)
                cal.set(Calendar.MINUTE,minute)

                btn_tiempo.text= SimpleDateFormat("HH:MM").format(cal.time)
            }

            TimePickerDialog(root.context,timeSetListener,cal.get(Calendar.HOUR_OF_DAY),cal.get(
                Calendar.MINUTE),true).show()

            //val textView: TextView = root.findViewById(R.id.text_dashboard)

            //dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
            //    textView.text = it
            //})
        }

        root.btn_save.setOnClickListener{
            var title=whatToDo.text.toString()
            var time=btn_tiempo.text.toString()

            var days= ArrayList<String>()

            if(lunesCheck.isChecked)
                days.add("Monday")

            if(martesCheck.isChecked)
                days.add("Tuesday")

            if(miercolesCheck.isChecked)
                days.add("Wednesday")

            if(juevesCheck.isChecked)
                days.add("Thursday")

            if(viernesCheck.isChecked)
                days.add("Friday")

            if(sabadoCheck.isChecked)
                days.add("Saturday")

            if(domingoCheck.isChecked)
                days.add("Sunday")

            var task= Task(title,days,time)

            HomeFragment.tasks.add(task)

            Toast.makeText(root.context,"new task addded", Toast.LENGTH_SHORT).show()
        }

        return root
    }
}

