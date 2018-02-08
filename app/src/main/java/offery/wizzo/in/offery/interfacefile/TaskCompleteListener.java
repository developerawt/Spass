package offery.wizzo.in.offery.interfacefile;

public interface TaskCompleteListener {
    public void onTaskComplete(int requestType, Object object);
    public void onTaskError(int requestType, Object object);
}
