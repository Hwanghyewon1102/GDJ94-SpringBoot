console.log("board");
const fileAdd = document.getElementById("fileAdd");
const files = document.getElementById("files");


  fileAdd.addEventListener("click", () => {

  const count = document.querySelectorAll('#files input[type="file"]').length;
  if (count >= 5) {
    alert("최대 5개까지 첨부할 수 있습니다.");
    return;
  }

    console.log("fileAdd");
    let div = document.createElement("div");
    let input = document.createElement("input");
    input.type = "file";
    let button = document.createElement("button");
    button.type = "button";
    input.name = "attach";
    div.append(input);
    div.append(button);
    button.innerText = "X";
    button.classList.add("del");
    files.append(div);
  });

  
files.addEventListener("click", (e) => {
  let element = e.target;
  if (element.classList.contains("del")) {
    element.parentElement.remove();
  }
  
});
