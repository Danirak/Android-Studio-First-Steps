package com.example.primeraprueba.todoapp

sealed class TaskCategory {
    object Personal: TaskCategory()
    object Business: TaskCategory()
    object Other: TaskCategory()
}
