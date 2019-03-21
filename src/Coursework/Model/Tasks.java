package Coursework.Model;

import java.util.ArrayList;

import static Coursework.Controller.TaskRead.LECTURE_ID;

public class Tasks {

    ArrayList<Task> tasks;

    public Tasks() {
        tasks = Database.loudTasks();

        ArrayList<Task> tempTasks = new ArrayList<>();

        for(Task task: tasks){
            if(task.getIdLecture() == LECTURE_ID){
                System.out.println("task for lecture " + task.getIdLecture());
                tempTasks.add(task);
            }
        }

        tasks = tempTasks;
    }

    public int countTasksByLection(int lectionID){
        int temp = 0;

        for(Task task: tasks){
            if(task.getIdLecture() == lectionID){
                temp++;
            }
        }
        return temp;
    }


    public Task getTaskByIndex(int index) {
        return tasks.get(index - 1);
    }

    public  boolean checkPassStudent(int pass){
        for (Task task : tasks) {
            if (task.getPassQuestion() == pass) {
                return true;
            }
        }
        return false;
    }
}
