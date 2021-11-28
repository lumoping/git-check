package com.github.lumoping.gitcheck.afterPull;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vcs.checkout.CheckoutListener;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;

public class PullCheck implements CheckoutListener {

    @Override
    public boolean processCheckedOutDirectory(@NotNull Project project, @NotNull Path directory) {
        return false;
    }

    @Override
    public void processOpenedProject(Project lastOpenedProject) {
        CheckoutListener.super.processOpenedProject(lastOpenedProject);
    }
}
