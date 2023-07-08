function doAlert(){
    alert('clicked button')
}

function doConfirm(){
    var choice = confirm('confirm this')
    if (choice){
        var message = "You pressed ok!"
    }
    if (!choice){
        var message = "Are you sure you want to cancel?"
    }
    alert(message)
}

function swapDiv1And2(){
    var d1 = document.getElement('div1')
    var d2 = document.getElement('div2')
    d1.className = 'div2'
    d2.className = 'div1'
}