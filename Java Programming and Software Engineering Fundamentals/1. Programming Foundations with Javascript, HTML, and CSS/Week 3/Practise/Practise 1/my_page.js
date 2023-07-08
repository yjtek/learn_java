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