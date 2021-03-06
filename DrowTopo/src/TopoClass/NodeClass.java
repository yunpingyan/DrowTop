package TopoClass;

import java.util.ArrayList;
import java.util.Hashtable;

public class NodeClass {
	public ArrayList<NodeClass> Parents;
	public ArrayList<NodeClass> childs;
	public String node_name;

	@SuppressWarnings("unchecked")
	public NodeClass(String str_name, ArrayList<String> str_P, Hashtable has_nodes,
			Hashtable has_ivys) {
		Parents = new ArrayList<NodeClass>();
		node_name = str_name;
		int i;
		for (i = 0; i < str_P.size(); i++) {

			NodeClass p = null;
			if (has_nodes.get(str_P.get(i)) == null) {
				ArrayList<String> str_des;
				if (has_ivys.containsKey(str_P.get(i))) {
					str_des = (ArrayList<String>) has_ivys
							.get(str_P.get(i));
				} else {
					str_des = new ArrayList<String>(0);
				}
				p = new NodeClass(str_P.get(i), str_des, has_nodes, has_ivys);
				has_nodes.put(p.node_name, p);
				p.childs = new ArrayList<NodeClass>();
				p.childs.add(this);
			} else {
				p = (NodeClass) has_nodes.get(str_P.get(i));
				if (p.childs == null)
					p.childs = new ArrayList<NodeClass>();
				p.childs.add(this);
			}
			if (p != null)
				Parents.add(p);
		}
	}

	public NodeClass() {

	}

	public NodeClass(String str_name) {
		node_name = str_name;
	}
}
