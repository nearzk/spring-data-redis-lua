local key = KEYS[1]
local survival_time = ARGV[1]
local max_times = ARGV[2]
local id = redis.call('get',key)
if(id == false)
then
    redis.call('set', key , max_times)
    print(key..' expire-time '..survival_time..' seconds')
    redis.call('expire',key, survival_time)
    return id
else
    redis.call('decr', key)
    local ttl = redis.call('ttl', key)
    print(key..' expires after '..ttl..' seconds')
    return id
end