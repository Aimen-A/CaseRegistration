if [ "$TRAVIS_BRANCH" != "slave" ]; then
    exit 0;
fi

export GIT_COMMITTER_EMAIL="aimenahmed29@gmail.com"
export GIT_COMMITTER_NAME="Aimen-A"

git checkout -b master || exit
git merge "$TRAVIS_COMMIT" || exit
git push "https://github.com/Aimen-A/CaseRegistration.git" master