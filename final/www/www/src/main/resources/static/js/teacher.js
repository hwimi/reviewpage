console.log("연결확인");

function showBigImage(imageSrc, description, tno) {
    console.log("Image Source: ", imageSrc);
    console.log("Description: ", description);
    console.log("TNO: ", tno);

    const mainImageDiv = document.getElementById('mainImage');
    mainImageDiv.innerHTML = `<img src="${imageSrc}" alt="큰 이미지">`;

    const text = document.getElementById('text');
    text.innerHTML = description;

    // 링크 업데이트
    const bigImageLink = document.getElementById('bigImageLink');
    bigImageLink.href = '/rev/reviewPage?tno=' + tno;

    // 메시지 업데이트
    const message = document.getElementById('message');
    message.innerText = "가운데 사진을 누르면 리뷰페이지로 이동합니다.";

    window.scrollTo(800, 800);
}

document.addEventListener('DOMContentLoaded', function() {
    let countBox = document.getElementById('count'),
        count = 0;

    let counting = setInterval(function () {
        if (count >= 15000) {
            count = 15325;
            clearInterval(counting);
        }
        countBox.innerHTML = `<span class="number">${new Intl.NumberFormat().format(count)}</span> 분의 선택을 받았습니다.`;
        count += 100;
    }, 20);

    // 스크롤 이벤트 감지하여 애니메이션 트리거
    const minTeacher = document.getElementById('min_teacher');

    function onScroll() {
        const rect = minTeacher.getBoundingClientRect();
        if (rect.top <= window.innerHeight) {
            minTeacher.classList.add('fade-in');
            window.removeEventListener('scroll', onScroll);
        }
    }

    window.addEventListener('scroll', onScroll);
});
