/*
 * Copyright (c) 2011, 2012 Roberto Tyley
 *
 * This file is part of 'Agit' - an Android Git client.
 *
 * Agit is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Agit is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see http://www.gnu.org/licenses/ .
 */

package com.madgag.agit.views;

import static com.madgag.agit.CommitViewerActivity.commitViewIntentFor;
import static com.madgag.agit.R.id.latest_commit;
import static com.madgag.agit.R.layout.latest_commit_view;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.google.inject.Inject;
import com.madgag.agit.R;
import com.madgag.agit.RepoSummary;

import org.eclipse.jgit.revwalk.RevCommit;

public class LatestCommitView extends FrameLayout implements EnabledListItem {
    private final RepoSummary repoSummary;

    @Inject
    public LatestCommitView(Context context, LayoutInflater layoutInflater, RepoSummary repoSummary) {
        super(context);
        this.repoSummary = repoSummary;
        layoutInflater.inflate(latest_commit_view, this);

        PrettyCommitSummaryView objectSummaryView = (PrettyCommitSummaryView) findViewById(latest_commit);
        RevCommit latestCommit = repoSummary.getLatestCommit();

        if (latestCommit == null) {
            objectSummaryView.setVisibility(GONE);
        } else {
            objectSummaryView.setCommit(latestCommit);
            objectSummaryView.setVisibility(VISIBLE);
        }
        objectSummaryView.setBackgroundResource(R.drawable.single_line_frame);
    }

    public void onItemClick() {
        if (repoSummary.hasCommits()) {
            getContext().startActivity(commitViewIntentFor(repoSummary.getRepo(), repoSummary.mostlyRecentlyUpdatedBranch.getRef()));
        }
    }

}
