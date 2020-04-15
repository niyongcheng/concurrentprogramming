# incrementAndGet() first use get() to retrieve the AtomicLong's long Value, this value is Volatile varialbe
# next it will increment 1 and use CAS function to setup the value

use volatile,native method and CAS to confirm the atomic operation