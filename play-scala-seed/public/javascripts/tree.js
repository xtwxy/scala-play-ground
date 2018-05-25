require(["jquery", "jquery-easyui"], function($, easyui) {
    $('#tree-node').tree({
        url: 'tree',
        method: 'get',
        loader: function(param,success,error) {
            $.get('tree', param, success).fail(error);
        }
    });
		var url = 'post';
	$('#newUser').on('click', function(){
			$('#dlg').dialog('open').dialog('center').dialog('setTitle','New User');
			$('#fm').form('clear');
			url = 'http://localhost:9000/post';
		});

	$('#editUser').on('click', function(){
			var row = $('#dg').datagrid('getSelected');
			if (row){
				$('#dlg').dialog('open').dialog('center').dialog('setTitle','Edit User');
				$('#fm').form('load',row);
				url = 'post?id='+row.id;
			}
		});

	$('#saveUser').on('click', function(){
			$('#fm').form('submit',{
				url: url,
				onSubmit: function(){
					return $(this).form('validate');
				},
				success: function(result){
				    alert(result);
					var result = eval('('+result+')');
					if (result.errorMsg){
						$.messager.show({
							title: 'Error',
							msg: result.errorMsg
						});
					} else {
						$('#dlg').dialog('close');		// close the dialog
						$('#dg').datagrid('reload');	// reload the user data
					}
				}
			});
		});

	$('#destroyUser').on('click', function(){
			var row = $('#dg').datagrid('getSelected');
			if (row){
				$.messager.confirm('Confirm','Are you sure you want to destroy this user?',function(r){
					if (r){
						$.post('post',{id:row.id},function(result){
							if (result.success){
								$('#dg').datagrid('reload');	// reload the user data
							} else {
								$.messager.show({	// show error message
									title: 'Error',
									msg: result.errorMsg
								});
							}
						},'json');
					}
				});
			}
		});
});
