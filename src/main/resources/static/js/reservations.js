function handleChangeOrderStatus(orderId, orderStatus) {
    const id = document.querySelector("#changeOrderStatusModal input[name=id]");
    const status = document.querySelector("#changeOrderStatusModal input[name=status]");
    const content = document.querySelector("#changeOrderStatusModal h3.content");
    if (id && status) {
        id.value = orderId;
        status.value = orderStatus;
    } else return;
    if (content) content.textContent = orderStatus + " reservation ?";
    $('#changeOrderStatusModal').modal('show');
}