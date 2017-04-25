
function deleteClient(){
	var idClient = $('#clientId').val();
	var clientDataVM = {id : idClient}; 
	$.ajax({
		type:"DELETE",
		url: "/client/deleteClient",
		data: JSON.stringify(clientDataVM),
		contentType: "application/json",
		cache: false,
		success: function(response) {
			alert("Cliente deletado com sucesso!");
			history.go(-1);
		},
		error: function(xhr) {
			history.go(-1);
		},
	});
};

function editClient(){
	var idClient = $('#clientId').val();
	window.location.href = "/client/editClient/"+idClient;
}

function deleteProduct(){
	var idProduct = $('#productId').val();
	var productDataVM = {id : idProduct}; 
	$.ajax({
		type:"DELETE",
		url: "/product/deleteProduct",
		data: JSON.stringify(productDataVM),
		contentType: "application/json",
		cache: false,
		success: function(response) {
			alert("Produto deletado com sucesso!");
			history.go(-1);
		},
		error: function(xhr) {
			history.go(-1);
		},
	});
};

function editProduct(){
	var idProduct = $('#productId').val();
	window.location.href = "/product/editProduct/"+idProduct;
}

function formatValue() {
	var value = parseFloat($('#price').val().replace(/,/g,'.'));
	$('#price').val(value);
}