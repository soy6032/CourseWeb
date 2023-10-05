
$(".logoutBtn").click(function() {
	if(confirm("로그아웃 하시겠습니까?")){
		location.href="/logout";
	}
})

var ahref = $(".collapse-item").attr("href");
var link = $(location).attr('pathname');
var last = link.substring(link.indexOf("/detail", 0));
var lastE = link.substring(link.indexOf("/Edit", 0));
var selUrl = link.replace(last, '');
var selUrlE = link.replace(lastE, '');

for (var i = 0; i <= $('.collapse-item').length; i++) {
	if ($(".collapse-item").eq(i).attr("href") == link
		|| $(".collapse-item").eq(i).attr("href") == selUrl
		|| $(".collapse-item").eq(i).attr("href") == selUrlE) {
		$(".collapse-item").eq(i).addClass('active');

		$(".collapse-item").eq(i).parent().parent()
			.addClass('show');
	}
}