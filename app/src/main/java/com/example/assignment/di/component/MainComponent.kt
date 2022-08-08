package com.example.assignment.di.component

import com.example.assignment.di.module.ApiModule
import com.example.assignment.di.scope.ComponentScope
import com.example.assignment.ui.view.MainActivity
import dagger.Component

@Component(modules = [ApiModule::class])
@ComponentScope
interface MainComponent {
    fun injectActivity(activity:MainActivity)
}