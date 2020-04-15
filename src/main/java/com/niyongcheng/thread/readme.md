thread status and transfer
1:NEW(new Thread,new Runnable)
2:Runnable(.start())
3:READY(yield)
4:Blocked(Sync)
5:WAITING(await,join)/TIMED_WAITING(sleep,await(long timeout) etc)
6:TERMINATED(stop(),interrupted())