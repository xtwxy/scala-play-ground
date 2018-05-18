require(["jquery", "./one", "./two"], function($, one, two) {
    one.hi();
    two.hi('world');

    $('h1').css('color','red');

    $('#submit').on('click', function() {
        console.log('#submit clicked.');
    });

    $('#btnAddProfile').on('click', function() {
        console.log('#btnAddProfile clicked.');
    });

    $('#btnAddProfile').prop('value', 'Save');
    console.log("And that's all");
});
