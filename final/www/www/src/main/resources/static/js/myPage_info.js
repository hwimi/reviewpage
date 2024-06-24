document.addEventListener('DOMContentLoaded', function () {
    const passwordInput = document.getElementById('sign_pwd');
    const checkPasswordInput = document.getElementById('sign_check_pwd');
    const stringPasswordMessage = document.querySelector('.stringPassword-message');
    const mismatchMessage = document.querySelector('.mismatch-message');
    // const form = document.getElementById('signup-form');

    // 비밀번호 유효성 검사 함수
    function validatePassword(password) {
        const regex = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$/;
        return regex.test(password);
    }

    // 비밀번호 입력 시 유효성 검사
    passwordInput.addEventListener('input', function () {
        if (!validatePassword(passwordInput.value)) {
            stringPasswordMessage.classList.remove('hide');
        } else {
            stringPasswordMessage.classList.add('hide');
        }
    });

    // 비밀번호 확인 입력 시 일치 여부 검사
    checkPasswordInput.addEventListener('input', function () {
        if (passwordInput.value !== checkPasswordInput.value) {
            mismatchMessage.classList.remove('hide');
        } else {
            mismatchMessage.classList.add('hide');
        }
    });

//    // 폼 제출 시 최종 유효성 검사
//    form.addEventListener('submit', function (event) {
//        if (!validatePassword(passwordInput.value)) {
//            stringPasswordMessage.classList.remove('hide');
//            event.preventDefault(); // 폼 제출 방지
//        }
//        if (passwordInput.value !== checkPasswordInput.value) {
//            mismatchMessage.classList.remove('hide');
//            event.preventDefault(); // 폼 제출 방지
//        }
//    });
});
