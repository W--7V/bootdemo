local current

local key = KEYS[1]
local flow = ARGV[1]
local limit = ARGV[2]
local expireTime = ARGV[3]


if tonumber(flow) > tonumber(limit)
then
    return false
end
current = redis.call("get",key)

if not current
then
    redis.call("set",key,flow)
    redis.call("expire",key,tonumber(expireTime))
else
    if tonumber(current)+tonumber(flow) > tonumber(limit)
    then
        return false
    end
    redis.call("setrange",key,0,tonumber(current)+tonumber(flow))
end

return true