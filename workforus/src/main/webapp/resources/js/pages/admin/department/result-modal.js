/**
 * 
 */


const resModal = new bootstrap.Modal(document.getElementById("resultModal"), {
	keyboard: false
})

const title = resModal._element.querySelector(".modal-title")
const body = resModal._element.querySelector(".modal-body")
title.innerText = data.title
body.innerHTML = "<p>" + data.message + "</p>"

resModal.show()
