package com.github.lumoping.gitcheck.prePush;

import com.github.lumoping.gitcheck.notifier.GitCheckNotifier;
import com.intellij.dvcs.push.PrePushHandler;
import com.intellij.dvcs.push.PushInfo;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.vcs.log.Hash;
import com.intellij.vcs.log.VcsFullCommitDetails;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class PushCheck implements PrePushHandler {

    @Override
    public @NotNull @Nls(capitalization = Nls.Capitalization.Title) String getPresentableName() {
        return "Git Check";
    }

    @Override
    public @NotNull Result handle(@NotNull List<PushInfo> pushDetails, @NotNull ProgressIndicator indicator) {
        Pattern pattern = Pattern.compile("Merge.*test.*into(?!.*test).*");
        List<VcsFullCommitDetails> errorCommits = new ArrayList<>();
        for (PushInfo pushInfo :
                pushDetails) {
            List<VcsFullCommitDetails> mergeTestCommits = pushInfo.getCommits()
                    .stream()
                    .filter(commit -> {
                        String commitMessage = commit.getFullMessage();
                        return pattern.matcher(commitMessage).matches();
                    })
                    .collect(Collectors.toList());
            errorCommits.addAll(mergeTestCommits);
        }
        String errorHashes = errorCommits.stream().distinct()
                .map(VcsFullCommitDetails::getId)
                .map(Hash::toShortString)
                .collect(Collectors.joining("\n"));
        String content = "test branch be merged! commitId:\n" + errorHashes;
        GitCheckNotifier.notifyError(content);


        return Result.ABORT_AND_CLOSE;
    }


}
