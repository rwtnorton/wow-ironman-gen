namespace WowIronmanGen;

public static class EnumSampler<T>
where T : struct, Enum
{
    public static T Sample(Random? random = null)
    {
        var values = Enum.GetValues<T>();
        return values[(random ?? (new Random())).Next(values.Length)];
    }
}
