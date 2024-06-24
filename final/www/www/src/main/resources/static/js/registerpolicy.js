console.log("registerpolicy in");


document.addEventListener('DOMContentLoaded', function() {
    const checkAllCheckbox = document.querySelector('._check_all');
    const joinButton = document.getElementById('join_button');
    const privacyAgree = document.getElementById('privacy_agree');
    const policyAgree = document.getElementById('policy_agree');
    const limitJoinAgree = document.getElementById('limit_join_agree');

    // 필수 체크 시 버튼 활성화
    function updateJoinButtonState() {
        if (privacyAgree.checked && policyAgree.checked && limitJoinAgree.checked) {
            joinButton.disabled = false;
        } else {
            joinButton.disabled = true;
        }
    }

    // 모두 동의 클릭 시 버튼 활성화
    function updateAllCheckboxes() {
        document.querySelectorAll('._join_agree').forEach(function(checkbox) {
            checkbox.checked = checkAllCheckbox.checked;
        });
        updateJoinButtonState();
    }

    privacyAgree.addEventListener('change', updateJoinButtonState);
    policyAgree.addEventListener('change', updateJoinButtonState);
    limitJoinAgree.addEventListener('change', updateJoinButtonState);
    checkAllCheckbox.addEventListener('click', updateAllCheckboxes);

    // 개별 체크 시 모두 동의 체크박스 활성화
    document.querySelectorAll('._join_agree').forEach(function(checkbox) {
        checkbox.addEventListener('change', function(){
            updateJoinButtonState();
            checkAllCheckbox.checked = privacyAgree.checked && policyAgree.checked && limitJoinAgree.checked;
        });
    });

    updateJoinButtonState();
});


// 마케팅 활용 동의 관련
document.addEventListener('DOMContentLoaded', function() {
    const marketingAgreeCheckbox = document.getElementById('marketing_agree');
    const smsAgreeCheckbox = document.getElementById('marketing_sms_agree');
    const emailAgreeCheckbox = document.getElementById('marketing_email_agree');

    function updateMarketingCheckboxState() {
        smsAgreeCheckbox.checked = this.checked;
        emailAgreeCheckbox.checked = this.checked;
    }

    function updateSMSCheckboxState() {
        if (smsAgreeCheckbox.checked && emailAgreeCheckbox.checked) {
            marketingAgreeCheckbox.checked = true;
        }
    }

    function updateEmailCheckboxState() {
        if (smsAgreeCheckbox.checked && emailAgreeCheckbox.checked) {
            marketingAgreeCheckbox.checked = true;
        }
    }

    marketingAgreeCheckbox.addEventListener('change', updateMarketingCheckboxState);
    smsAgreeCheckbox.addEventListener('change', updateSMSCheckboxState);
    emailAgreeCheckbox.addEventListener('change', updateEmailCheckboxState);

    updateMarketingCheckboxState();
    updateSMSCheckboxState();
    updateEmailCheckboxState();
});

