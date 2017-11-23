<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Quartz</title>
	<link rel="stylesheet" type="text/css" href="/css/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="/css/easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="/css/easyui/themes/color.css">
	<script type="text/javascript" src="/js/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="/js/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="/js/easyui/datagrid-detailview.js"></script>
</head>
<body>
	<h2>Quartz配置</h2>
	<table id="jobList" title="任务列表" class="easyui-datagrid" style="width:100%;height:850px"
		data-options="singleSelect:true,toolbar:'#toolbar',rownumbers:true,fitColumns:true">
    <thead>
        <tr>
            <th data-options="field:'oper',width:80,formatter:formatOper">操作</th>
            <th data-options="field:'group',width:100">任务组</th>
            <th data-options="field:'jobName',width:160">任务名</th>
            <th data-options="field:'jobClass',width:240">执行类</th>
            <th data-options="field:'desc',width:300">描述</th>
        </tr>
    </thead>
    </table>
	<div id="toolbar" style="padding:3px">
	    <span>任务组:</span>
	    <select id="groupSelect" class="easyui-combobox" panelHeight="auto" style="width:100px">
            <option >-请选择-</option>
	    	<#list groups as item>
            <option value="${item}">${item}</option>
            </#list>
        </select>
	    <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="doSearch()">搜索</a>
	    <span>&nbsp;&nbsp;&nbsp;</span>
	    <a href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="showJobForm()">新增任务</a>
	</div>
    <div id="mm1" class="easyui-menu">
        <div data-options="iconCls:'icon-remove'" id="menuRemove">删除</div>
        <div data-options="iconCls:'icon-man'" id="menuRun">运行任务</div>
        <div data-options="iconCls:'icon-add'" id="menuAddTrigger">添加触发</div>
    </div>
    <div id="jobdlg" class="easyui-dialog" title="新增任务" data-options="closed:true" style="width:450px;height:350px;padding:5px">
        <div style="padding:10px 30px 20px 30px">
        <form id="jobForm" method="post">
            <table cellpadding="5">
                <tr>
                    <td>任务组:</td>
                    <td>
                    <select name="group" class="easyui-combobox" data-options="required:true" panelHeight="auto" style="width:100px">
				    	<#list groups as item>
			            <option value="${item}">${item}</option>
			            </#list>
			        </select>
                    </td>
                </tr>
                <tr>
                    <td>任务名:</td>
                    <td><input class="easyui-textbox" type="text" name="jobName" data-options="required:true"></input></td>
                </tr>
                <tr>
                    <td>执行类:</td>
                    <td><input class="easyui-textbox" type="text" name="jobClassName" data-options="required:true" style="width:280px"></input></td>
                </tr>
                <tr>
                    <td>描述:</td>
                    <td><input class="easyui-textbox" name="desc" data-options="multiline:true" style="height:80px;width:280px"></input></td>
                </tr>
            </table>
        </form>
        <div style="text-align:center;padding:5px">
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitJobForm()">提交</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearJobForm()">清除</a>
        </div>
        </div>
    </div>
    <div id="triggerdlg" class="easyui-dialog" title="新增触发" data-options="closed:true" style="width:450px;height:280px;padding:5px">
        <div style="padding:10px 30px 20px 30px">
        <form id="triggerForm" method="post">
            <table cellpadding="5">
                <tr>
                    <td>任务组:</td>
                    <td><input class="easyui-textbox" type="text" name="group" data-options="readonly:true,required:true"></input></td>
                </tr>
                <tr>
                    <td>任务名:</td>
                    <td><input class="easyui-textbox" type="text" name="jobName" data-options="readonly:true,required:true"></input></td>
                </tr>
                <tr>
                    <td>触发名:</td>
                    <td><input class="easyui-textbox" type="text" name="triggerName" data-options="required:true"></input></td>
                </tr>
                <tr>
                    <td>Cron表达式:</td>
                    <td><input class="easyui-textbox" name="cronExpression" data-options="required:true" style="width:200px"></input></td>
                </tr>
            </table>
        </form>
        <div style="text-align:center;padding:5px">
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitTriggerForm()">提交</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearTriggerForm()">清除</a>
        </div>
        </div>
    </div>
	<script type="text/javascript">
		function showJobForm(){
			$('#jobdlg').dialog('open');
		}
		function submitJobForm(){
            $('#jobForm').form('submit', {
            	url: "/quartz/job/add",
			    success:function(data){
			    	var data = eval('(' + data + ')');
			    	var tip = "";
			        if(!data.status){
			        	tip = '提交失败';
			        } else {
			        	tip = '提交成功';
			        	$('#jobdlg').dialog('close');
			        	doSearch();
			        }
			        $.messager.show({
		                title:'提示',
		                msg:tip,
		                timeout:1000,
		                showType:'slide'
		            });
			    }
            });
        }
        function clearJobForm(){
            $('#jobForm').form('clear');
        }
        
		function formatOper(val, row, index){
			return '<a href="#" id="mb' + index + '" class="easyui-menubutton" iconCls="icon-edit" onclick="showMenu(' + index + ')">'+this.title+'</a>';
		}
		function showMenu(key) {
			$('#mb'+key).menubutton({
			    menu: '#mm1'
			});
			$('#mm1').menu({
				onClick: function (item) {
					var func;
					if(item.id) {
						func = eval(item.id);
					}
					if(func && $.isFunction(func)) {
						item.onclick = eval(item.id + "(" + key + ")");
					}
				}
			});
		}
		
		function menuRemove(idx){
			var row = $('#jobList').datagrid('getRows')[idx];
			$.messager.confirm('确认', '确认删除任务?', function(r){
				if (r){
					$.ajax({
						url: "/quartz/job/del", 
						type: "POST",
						contentType: "application/json",
						data: JSON.stringify({ group: row.group, jobName: row.jobName }),
						success: function(data){
							if(!data.status){
								$.messager.show({
					                title:'提示',
					                msg:'删除失败',
					                timeout:1000,
					                showType:'slide'
					            });
					            return;
							}
							doSearch();
						}
					});
				}
			});
		}
		
		function menuRun(idx){
			var row = $('#jobList').datagrid('getRows')[idx];
			$.ajax({
				url: "/quartz/job/execute", 
				type: "POST",
				contentType: "application/json",
				data: JSON.stringify({ group: row.group, jobName: row.jobName }),
				success: function(data){
					var tip = '';
					if(!data.status){
						tip = data.msg;
					} else {
						tip = '正在运行中...';
					}
					$.messager.show({
		                title: '提示',
		                msg: tip,
		                timeout: 1000,
		                showType: 'slide'
		            });
				}
			});
		}
		
		function menuAddTrigger(idx){
			var row = $('#jobList').datagrid('getRows')[idx];
			$('#triggerForm').form('load',{
                group: row.group,
                jobName: row.jobName
            });
			$('#triggerdlg').dialog('open');
		}
		
		function submitTriggerForm(){
            $('#triggerForm').form('submit', {
            	url: "/quartz/trigger/add",
			    success:function(data){
			    	var data = eval('(' + data + ')');
			    	var tip = "";
			        if(!data.status){
			        	tip = '提交失败';
			        } else {
			        	tip = '提交成功';
			        	$('#triggerdlg').dialog('close');
			        	doSearch();
			        }
			        $.messager.show({
		                title:'提示',
		                msg:tip,
		                timeout:1000,
		                showType:'slide'
		            });
			    }
            });
        }
        function clearTriggerForm(){
            $('#jobForm').form('clear');
        }
        
        function formatTrigOper(val, row, index){
        	var key = row.group + row.jobName;
        	var html = 
			 '<a href="#" onclick="removeTrigger(\'' + key + '\',' + index + ')">删除</a>';
			html += '&nbsp;&nbsp;<a href="#" onclick="switchTrigger(\'' + key + '\',' + index + ')">开/关</a>';
			return html;
        }
        
        function switchTrigger(key, idx){
        	var ddv;
        	$.each(subGrid, function(i, n){
        		if(n.key == key){
        			ddv = n.subgrid;
        			return false;
        		}
			});
        	var row = ddv.datagrid('getRows')[idx];
        	$.ajax({
				url: "/quartz/trigger/toggle", 
				type: "POST",
				contentType: "application/json",
				data: JSON.stringify({ group: row.group, triggerName: row.triggerName }),
				success: function(data){
					if(!data.status){
						$.messager.show({
			                title:'提示',
			                msg:data.msg,
			                timeout:1000,
			                showType:'slide'
			            });
			            return;
					}
					ddv.datagrid({
						data: queryTriggerList(row.group, row.jobName)
					});
				}
			});
        }
        
        function removeTrigger(key, idx){
        	var ddv;
        	$.each(subGrid, function(i, n){
        		if(n.key == key){
        			ddv = n.subgrid;
        			return false;
        		}
			});
			
        	var row = ddv.datagrid('getRows')[idx];
			$.ajax({
				url: "/quartz/trigger/del", 
				type: "POST",
				contentType: "application/json",
				data: JSON.stringify({ group: row.group, triggerName: row.triggerName }),
				success: function(data){
					if(!data.status){
						$.messager.show({
			                title:'提示',
			                msg:'删除失败',
			                timeout:1000,
			                showType:'slide'
			            });
			            return;
					}
					
					ddv.datagrid({
						data: queryTriggerList(row.group, row.jobName)
					});
				}
			});
        }
        var subGrid = [];
		
		$('#jobList').datagrid({
		    view: detailview,
		    detailFormatter:function(index,row){
		        return '<div style="padding:2px"><table class="ddv"></table></div>';
		    },
		    onExpandRow: function(index,row){
		        var parentGrid = $(this);
		        var ddv = parentGrid.datagrid('getRowDetail',index).find('table.ddv');
		        subGrid.push({key: row.group + row.jobName, subgrid: ddv});
		        ddv.datagrid({
		            fitColumns:true,
		            singleSelect:true,
		            rownumbers:true,
		            loadMsg:'',
		            height:'auto',
		            data: queryTriggerList(row.group, row.jobName),
		            columns:[[
		                {field:'group',title:'任务组',hidden:true,width:80},
		                {field:'jobName',title:'任务名',hidden:true,width:100},
		                {field:'oper',title:'操作',width:80,formatter:formatTrigOper},
		                {field:'triggerName',title:'触发器名',width:100},
		                {field:'triggerType',title:'类型',width:90},
		                {field:'state',title:'状态',width:50},
		                {field:'desc',title:'描述',width:200}
		            ]],
		            onResize:function(){
		                parentGrid.datagrid('fixDetailRowHeight',index);
		            },
		            onLoadSuccess:function(){
		                setTimeout(function(){
		                    parentGrid.datagrid('fixDetailRowHeight',index);
		                },0);
		            }
		        });
		        parentGrid.datagrid('fixDetailRowHeight', index);
		    }
		});
		
		function queryTriggerList(group, jobName){
			var ret;
			$.ajax({
				url: "/quartz/trigger/list", 
				type: "POST",
				async: false,
				contentType: "application/json",
				data: JSON.stringify({ group: group, jobName: jobName }),
				success: function(data){
					ret = data.data;
				}
			});
			return ret;
		}

		function doSearch(){
			subGrid = [];
			var group = $("#groupSelect").combobox('getValue');
			if(!group) return;
			$.ajax({
				url: "/quartz/job/list", 
				type: "POST",
				contentType: "application/json",
				data: JSON.stringify({ group: $("#groupSelect").combobox('getValue') }),
				success: function(data){
			     	$('#jobList').datagrid({
						data: data.data,
						onLoadSuccess: function (data) {
                    		for (i = 0; i < $('#jobList').datagrid('getRows').length; i++) {        
                        		$('#mb' + i).menubutton();
                   	 		}
                		}
					});
				}
			});
		}
	</script>
</body>
</html>