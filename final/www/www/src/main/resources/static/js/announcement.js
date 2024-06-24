console.log("연결확인")
document.getElementById('button').addEventListener('click', (e) => {
    const name = document.getElementById('name').value;
    const number = document.getElementById('number').value;
    const text_area = document.getElementById('text_area').value;

    if (name&&number&&text_area) {
        alert("전송이 완료되었습니다");

        // 입력 필드 초기화
        document.getElementById('name').value = '';
        document.getElementById('number').value = '';
        document.getElementById('text_area').value = '';
    } else {
        alert('모든 필드를 채워주세요');
    }
});
