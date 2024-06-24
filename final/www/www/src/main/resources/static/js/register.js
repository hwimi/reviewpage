console.log("register in");


async function sendCheckIdFromServer(id){
    try {
        url = "/member/checkSignId/" + id;
        config = {
            method : "get"
        }
        const resp = await fetch(url, config);
        const result = await resp.text();

        return result;

    } catch (error) {
        console.log(error);
    }
}

// 아이디 중복체크
document.getElementById('check_idBtn').addEventListener('click', ()=>{
    const signId = document.getElementById('sign_id').value;
    console.log(signId);

    if(signId == null || signId == ""){
        alert("아이디를 입력해주세요.")
        return false;
    }else{
        sendCheckIdFromServer(signId).then(result=>{
            if(result=='0'){
                alert("중복된 아이디 입니다.")
            }else{
                alert("사용가능한 아이디 입니다.")
                return true;
            }
        })
    }
})


//////////////////////////////////////////////////////////////////////////////

let checkID = document.getElementById('sign_id');
let checkIDsucessMessage = document.querySelector('.success-message');
let checkIDfailureMessage = document.querySelector('.failure-message');
let checkIDfailureMessage2 = document.querySelector('.failure-message2');
let checkPW = document.getElementById('sign_pwd');
let checkPW2 = document.getElementById('sign_check_pwd');
let checkPWfailMessage = document.querySelector('.mismatch-message');
let checkPWstrongfailMessage = document.querySelector('.stringPassword-message');
let sbtn = document.getElementById('signInBtn');

// ID 유효성 검사
function idLength(idl) {
    return idl.length >= 4 && idl.length <= 12;
}

function onlyNumAndEng(str) {
    return /^[A-Za-z0-9]*$/.test(str);
}

function strongPassword(str) {
    return /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$/.test(str);
}

function matchPW(password1, password2) {
    return password1 === password2;
}

function validateID() {
    let id = checkID.value;
    if (id.length !== 0) {
        if (!onlyNumAndEng(id)) {
            checkIDsucessMessage.classList.add('hide');
            checkIDfailureMessage.classList.add('hide');
            checkIDfailureMessage2.classList.remove('hide');
        } else if (!idLength(id)) {
            checkIDsucessMessage.classList.add('hide');
            checkIDfailureMessage.classList.remove('hide');
            checkIDfailureMessage2.classList.add('hide');
        } else {
            checkIDsucessMessage.classList.remove('hide');
            checkIDfailureMessage.classList.add('hide');
            checkIDfailureMessage2.classList.add('hide');
        }
    } else {
        checkIDsucessMessage.classList.add('hide');
        checkIDfailureMessage.classList.add('hide');
        checkIDfailureMessage2.classList.add('hide');
    }
    updateSignBtn();
}

// PWD 유효성 검사
function validatePW() {
    let pw = checkPW.value;
    if (pw.length !== 0) {
        if (strongPassword(pw)) {
            checkPWstrongfailMessage.classList.add('hide');
        } else {
            checkPWstrongfailMessage.classList.remove('hide');
        }
    } else {
        checkPWstrongfailMessage.classList.add('hide');
    }
    validatePWMatch();
    updateSignBtn();
}

// 비밀번호 일치 검사
function validatePWMatch() {
    let pw = checkPW.value;
    let pw2 = checkPW2.value;
    if (pw2.length !== 0) {
        if (matchPW(pw, pw2)) {
            checkPWfailMessage.classList.add('hide');
        } else {
            checkPWfailMessage.classList.remove('hide');
        }
    } else {
        checkPWfailMessage.classList.add('hide');
    }
    updateSignBtn();
}

// 회원가입 버튼 활성화 상태 업데이트
function updateSignBtn() {
    if (checkID.value.length !== 0 && idLength(checkID.value) && onlyNumAndEng(checkID.value) 
    && matchPW(checkPW.value, checkPW2.value) && strongPassword(checkPW.value) && checkPW.value.length !== 0) {
        sbtn.disabled = false;
    } else {
        sbtn.disabled = true;
    }
}



// 이벤트 리스너 설정
checkID.addEventListener('keyup', validateID);
checkPW.addEventListener('keyup', validatePW);
checkPW2.addEventListener('keyup', validatePWMatch);

// 초기 상태 설정
updateSignBtn();
