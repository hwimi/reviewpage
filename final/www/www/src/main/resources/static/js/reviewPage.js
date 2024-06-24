document.addEventListener('DOMContentLoaded', function() {
    const cmtAddBtn = document.getElementById('cmtAddBtn');
    if (cmtAddBtn) {
        cmtAddBtn.addEventListener('click', async () => {
            const cmtText = document.getElementById('cmtText').value;
            const cmtWriter = document.getElementById('cmtWriter').innerHTML;
            const cmtScore = document.getElementById('cmtScore').value;
            const tnoElement = document.querySelector('.review_image');
            const tno = tnoElement ? parseInt(tnoElement.dataset.tno, 10) : NaN;

            if (cmtText == null || cmtText === '') {
                alert("댓글을 입력해주세요");
                document.getElementById('cmtText').focus();
                return false;
            } else {
                let cmtData = {
                    tno: tno,
                    writer: cmtWriter,
                    content: cmtText,
                    score: cmtScore
                };

                const result = await postCommentServer(cmtData);
                if (result === "1") {
                    alert("댓글이 등록되었습니다.");
                    document.getElementById('cmtText').value = '';
                } else {
                    alert("댓글 등록에 실패했습니다.");
                }
            }
            spreadCommentList(tno, 1); // page를 1로 설정
            updateCommentStats(tno); // 댓글 수 및 평균 점수 갱신
        });
    }

    const tnoElement = document.querySelector('.review_image');
    const tno = tnoElement ? parseInt(tnoElement.dataset.tno, 10) : NaN;
    spreadCommentList(tno, 1); // 페이지 로드 시 댓글 목록을 불러옴, page를 1로 설정
    updateCommentStats(tno); // 페이지 로드 시 댓글 수 및 평균 점수 불러옴

    document.getElementById('cmtListArea').addEventListener('click', async (e) => {
        if (e.target.classList.contains('del')) {
            const cno = e.target.dataset.cno;
            const result = await deleteComment(cno);
            if (result === "1") {
                alert("댓글이 삭제되었습니다.");
                spreadCommentList(tno, 1); // 삭제 후 댓글 목록 갱신
                updateCommentStats(tno); // 댓글 수 및 평균 점수 갱신
            } else {
                alert("댓글 삭제에 실패했습니다.");
            }
        } else if (e.target.classList.contains('mod')) {
            const cno = e.target.dataset.cno;
            const content = e.target.closest('li').querySelector('.comment-content').textContent.replace('리뷰 내용:', '');
            const score = e.target.closest('li').querySelector('.comment-score').dataset.score;
            document.getElementById('modifyCno').value = cno;
            document.getElementById('modifyContent').value = content;
            document.getElementById('modifyScore').value = score;
        }
    });

    document.getElementById('saveModifyBtn').addEventListener('click', async () => {
        const cno = document.getElementById('modifyCno').value;
        const content = document.getElementById('modifyContent').value;
        const score = document.getElementById('modifyScore').value;

        if (content == null || content === '') {
            alert("내용을 입력해주세요");
            document.getElementById('modifyContent').focus();
            return false;
        } else {
            let modifyData = {
                cno: cno,
                content: content,
                score: score
            };
            const result = await modifyComment(modifyData);
            if (result === "1") {
                alert("댓글이 수정되었습니다.");
                document.querySelector('.btn-close').click();
                const tnoElement = document.querySelector('.review_image');
                const tno = tnoElement ? parseInt(tnoElement.dataset.tno, 10) : NaN;
                spreadCommentList(tno, 1); // 수정 후 댓글 목록 갱신
                updateCommentStats(tno); // 댓글 수 및 평균 점수 갱신
            } else {
                alert("댓글 수정에 실패했습니다.");
            }
        }
    });

    const descriptions = {
        1: '안녕하세요 저는 <span>철수</span> 입니다<br>저는 강아지를 12년째 키우고 있습니다<br>저는 산책 샤워 서비스가 가능합니다<br>여러분의 소중한 가족 저의 가족이라고 생각하겠습니다',
        2: '안녕하세요 저는 <span>영희</span> 입니다<br>저의 경력은 강아지 5년째 고양이를 3년째 키우고 있습니다<br>저는 24시간 서비스가 가능합니다 필요하시면 꼭 불러주세요<br>언제나 만족하는 서비스를 선사하겠습니다',
        3: '안녕하세요 저는 <span>유쥰</span> 입니다<br>저는 캣매니저 공인 자격증을 가지고 있습니다<br>여러분의 소중한 고양이 저한테 맡겨주세요',
        4: '안녕하세요 저는 <span>이현</span> 입니다<br>저의 경력은 강아지 키우기 몇년<br>고양이 키우기 몇년 입니다<br>당신의 소중한 가족 저한테 맡겨주세요',
        5: '안녕하세요 저는 <span>대현</span> 입니다<br>어디가 가야 하실때 애완동물 때문에 걱정이시죠<br>혼자 있을까 걱정이 되신다면<br>언제나 저한테 연락을 주세요',
        6: '안녕하세요 저는 <span>수아</span> 입니다<br>저는 늘 여러분과 함께 합니다<br>도움이 필요하시다면<br>언제든 연락을 주세요'
    };

    const descriptionDiv = document.getElementById('description');
    descriptionDiv.innerHTML = descriptions[tno] || '설명이 제공되지 않습니다.';
});

function spreadCommentList(tno, page = 1) {
    CommentList(tno, page).then(result => {
        const ul = document.getElementById('cmtListArea');
        const commentCountDiv = document.getElementById('commentCount');
        const averageScoreDiv = document.getElementById('averageScore');
        const username = document.getElementById('cmtWriter').innerHTML; // 현재 로그인된 사용자 이름 가져오기

        if (result.length > 0) {
            if (page === 1) {
                ul.innerHTML = '';
            }
            for (let cvo of result) {
                let li = `<li class="list-group-item" data-tno="${cvo.tno}">`;
                li += `<div class="teacher-name">매니저 이름: ${cvo.teacherName}</div>`;
                li += `<div class="comment-writer">작성자: ${cvo.writer}</div>`;
                li += `<div class="comment-content">리뷰 내용:${cvo.content}</div>`;
                li += `<div class="comment-score" data-score="${cvo.score}">${getStarRating(cvo.score)}</div>`;
                li += `<div class="comment-regdate">작성시간:${cvo.regDate}</div>`;
                if (cvo.writer === username) { // 현재 로그인된 사용자가 댓글 작성자인 경우에만 수정/삭제 버튼 보이기
                    li += `<button type="button" class="btn btn-outline-warning btn-sm mod" data-bs-toggle="modal" data-bs-target="#myModal" data-cno="${cvo.cno}">수정</button>`;
                    li += `<button type="button" data-cno="${cvo.cno}" class="btn btn-outline-danger btn-sm del">삭제</button>`;
                }
                ul.innerHTML += li;
            }
            commentCountDiv.innerHTML = `댓글 수: ${result.length}`;
            updateCommentStats(tno); // 댓글 수 및 평균 점수 갱신
        } else {
            ul.innerHTML = '댓글이 없습니다.';
            commentCountDiv.innerHTML = '';
            averageScoreDiv.innerHTML = '';
        }
    }).catch(error => {
        console.error("댓글 목록을 불러오는 중 오류 발생: ", error);
    });
}

function updateCommentStats(tno) {
    fetch(`/rev/commentCount?tno=${tno}`)
        .then(response => response.json())
        .then(count => {
            const commentCountDiv = document.getElementById('commentCount');
            commentCountDiv.innerHTML = count > 0 ? `댓글 수: ${count}` : '';
        })
        .catch(error => {
            console.error("댓글 수를 불러오는 중 오류 발생: ", error);
        });

    fetch(`/rev/averageScore?tno=${tno}`)
        .then(response => response.json())
        .then(averageScore => {
            const averageScoreDiv = document.getElementById('averageScore');
            averageScoreDiv.innerHTML = averageScore > 0 ? `평균 별점: ${getStarRating(averageScore)}` : '';
        })
        .catch(error => {
            console.error("평균 점수를 불러오는 중 오류 발생: ", error);
        });
}

function getStarRating(score) {
    const fullStars = Math.floor(score);
    const hasHalfStar = score % 1 !== 0;
    const emptyStars = 5 - fullStars - (hasHalfStar ? 1 : 0);

    let starsHtml = '';
    for (let i = 0; i < fullStars; i++) {
        starsHtml += '<span class="star full">★</span>';
    }
    if (hasHalfStar) {
        starsHtml += '<span class="star half">☆</span>';
    }
    for (let i = 0; i < emptyStars; i++) {
        starsHtml += '<span class="star empty">☆</span>';
    }
    return starsHtml;
}

async function postCommentServer(cmtData) {
    try {
        const url = "/rev/post";
        const config = {
            method: "post",
            headers: {
                "Content-Type": "application/json; charset=utf-8"
            },
            body: JSON.stringify(cmtData)
        };
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}

async function CommentList(tno, page) {
    try {
        const resp = await fetch(`/rev/list?tno=${tno}&page=${page}`);
        const result = await resp.json();
        return result;
    } catch (error) {
        console.log(error);
    }
}

async function deleteComment(cno) {
    try {
        const url = `/rev/delete?cno=${cno}`;
        const config = {
            method: "delete"
        };
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}

async function modifyComment(modifyData) {
    try {
        const url = "/rev/modify";
        const config = {
            method: "put",
            headers: {
                "Content-Type": "application/json; charset=utf-8"
            },
            body: JSON.stringify(modifyData)
        };
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}
