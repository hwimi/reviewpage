console.log("연결확인2222");

    const titleInput = document.getElementById("title");
    const writerInput = document.getElementById('writer');
    const addBtn = document.getElementById("addBtn");

    document.addEventListener('input', (e) => {
        console.log("111");
        console.log(titleInput);
        var titleValue = titleInput.value
        var writerValue = writerInput.value

        if (titleValue === '' || writerValue === '') {
            addBtn.disabled = true;
            //alert("제목과 작성자를 입력해주세요");
        } else {
            addBtn.disabled = false;
        }
    });

