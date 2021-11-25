package com.github.lumoping.gitcheck.services

import com.intellij.openapi.project.Project
import com.github.lumoping.gitcheck.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
