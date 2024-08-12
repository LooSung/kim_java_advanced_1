package thread.collection.simple.list;

public class SyncProxyList implements SimpleList {

	private SimpleList target;

	public SyncProxyList(SimpleList target) {
		this.target = target;
	}

	@Override synchronized public int size() {
		return target.size();
	}

	@Override synchronized public void add(Object o) {
		target.add(o);
	}

	@Override synchronized public Object get(int index) {
		return target.get(index);
	}

	@Override
	public String toString() {
		return target.toString() + "  by "  + this.getClass().getSimpleName();
	}
}
