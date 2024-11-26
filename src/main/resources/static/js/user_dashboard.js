function handleOrderRoomClick(room) {
    const id = document.querySelector("#orderRoomModal input[name=roomId]");
    const name = document.querySelector("#orderRoomModal .room-name");
    const type = document.querySelector("#orderRoomModal .room-type");
    const price = document.querySelector("#orderRoomModal .room-price");
    // const price = document.ge("#orderRoomModal .room-price");
    if (id) {
        id.value = room.id;
    } else return;
    if (name) name.value = room.name;
    if (type) type.value = room.type;
    if (price) price.value = room.price;
    $('#orderRoomModal').modal('show');
}

function handleDateChange() {
    const checkinDate = document.querySelector("#orderRoomModal input[name=checkinDate]");
    const checkoutDate = document.querySelector("#orderRoomModal input[name=checkoutDate]");
    const orderPrice = document.querySelector("#orderRoomModal input[name=price]");
    const orderPriceFormat = document.querySelector("#orderRoomModal .priceFormat");
    const roomPrice = document.querySelector("#orderRoomModal .room-price");
    if (!checkinDate || !checkoutDate || !roomPrice || !orderPrice || !orderPriceFormat) {
        return;
    }
    const checkinDateValue = new Date(checkinDate.value);
    const checkoutDateValue = new Date(checkoutDate.value);
    const timeDifference = checkoutDateValue.getTime() - checkinDateValue.getTime()
    if (timeDifference <= 0 || isNaN(checkinDateValue.getTime()) || isNaN(checkoutDateValue.getTime())) {
        return;
    }
    const dayDifference = Math.ceil(timeDifference / (1000 * 60 * 60 * 24));
    orderPrice.value = Number(dayDifference * roomPrice.value);
    orderPriceFormat.value = Number(dayDifference * roomPrice.value).toLocaleString('en-US') + " VNĐ";
}