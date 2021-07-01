package sqlnode;


import sqlnode.iface.SqlNode;
import uitls.OgnlUtils;

public class IfSqlNode implements SqlNode {

	private String test;

	private SqlNode rootSqlNode;

	public IfSqlNode(String test, MixedSqlNode rootSqlNode) {
		this.test = test;
		this.rootSqlNode = rootSqlNode;
	}

	@Override
	public void apply(DynamicContext context) {

		boolean testValue = OgnlUtils.evaluateBoolean(test, context.getBindings().get("param"));
		if (testValue) {
			rootSqlNode.apply(context);
		}
	}

}
