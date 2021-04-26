# tdt4240_project

TDT4240 Programvarearkitektur gruppe 27

## Contributing

We work on changes on a local branch. Try to have one commit per issue, and one issue per PR.

Main is the default branch we work on.

### Commit message

```
PA-x: message in imperative
```

Where x is the issue number. All lowercase except 'MAT'.

For smaller changes that doesn't need an issue, use:

```
chore: message in imperative
```

### PR name

Use same (or close) as commit message.

If there is one PR for multiple issues (which we try to avoid, but it can happen), use `PA-x/y`, where x and y are the issue numbers.

### Reviewing

At least one person has to approve PR before merging.

After all comments are resolved, the author can merge the PR.

### Resolving comments

We append all fixes to the commit instead of making new commits. After comment is resolved, commit with:

```
git commit --amend --no-edit
```

If you need to change the commit message use this instead:

```
git commit --amend
```

Since the upstream now differs from your local branch, use force push.

```
git push -f
```

(use with caution)

### Avoid merge conflicts

If you're working on a branch, and another PR has been pushed before you're able to push, we rebase on develop before we push to avoid any conflicts.

1. Commit changes in feature-branch
2. Checkout to develop
3. Pull latest changes
4. Checkout to feature-branch
5. `git rebase develop`

This can result in Merge Conflicts. If this happens, just resolve the conflict, save, and stage the file. You don't need to change the commit message if it isn't necessary. Then

```
git rebase --continue
```

You can also abort the rebase if you wish, with

```
git rebase --abort
```

### Merge

We use rebase and merge strategy. This way we get a clean commit history. Branch is deleted upon merge, while your local is not.
