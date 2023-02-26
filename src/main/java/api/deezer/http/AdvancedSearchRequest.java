package api.deezer.http;

/**
 * Executes Deezer API advanced search request.
 *
 * @param <T> response POJO type.
 */
public class AdvancedSearchRequest<T> extends SearchRequest<T> {
    public AdvancedSearchRequest(String url, Class<T> answerClass) {
        super(url, answerClass);
    }

    @Override
    public AdvancedSearchRequest<T> addParam(String name, String value) {
        return (AdvancedSearchRequest<T>) super.addParam(name, value);
    }

    @Override
    public AdvancedSearchRequest<T> limit(int limit) {
        return (AdvancedSearchRequest<T>) super.limit(limit);
    }

    @Override
    public AdvancedSearchRequest<T> index(int index) {
        return (AdvancedSearchRequest<T>) super.index(index);
    }

    /**
     * Adds <b>artist</b> parameter.
     *
     * @param artist artist name.
     * @return current instance.
     */
    public AdvancedSearchRequest<T> artist(String artist) {
        return mergeQ("artist", str(artist));
    }

    /**
     * Adds <b>album</b> parameter.
     *
     * @param album album name.
     * @return current instance.
     */
    public AdvancedSearchRequest<T> album(String album) {
        return mergeQ("album", str(album));
    }

    /**
     * Adds <b>track</b> parameter.
     *
     * @param track track name.
     * @return current instance.
     */
    public AdvancedSearchRequest<T> track(String track) {
        return mergeQ("track", str(track));
    }

    /**
     * Adds <b>label</b> parameter.
     *
     * @param label label.
     * @return current instance.
     */
    public AdvancedSearchRequest<T> label(String label) {
        return mergeQ("label", str(label));
    }

    /**
     * Adds <b>dur_min</b> parameter.
     *
     * @param durMin minimum duration.
     * @return current instance.
     */
    public AdvancedSearchRequest<T> durMin(int durMin) {
        return mergeQ("dur_min", String.valueOf(durMin));
    }

    /**
     * Adds <b>dur_max</b> parameter.
     *
     * @param durMax maximum duration.
     * @return current instance.
     */
    public AdvancedSearchRequest<T> durMax(int durMax) {
        return mergeQ("dur_max", String.valueOf(durMax));
    }

    /**
     * Adds <b>bpm_min</b> parameter.
     *
     * @param bpmMin minimum BPM.
     * @return current instance.
     */
    public AdvancedSearchRequest<T> bpmMin(int bpmMin) {
        return mergeQ("bpm_min", String.valueOf(bpmMin));
    }

    /**
     * Adds <b>bpm_max</b> parameter.
     *
     * @param bpmMax maximum BPM.
     * @return current instance.
     */
    public AdvancedSearchRequest<T> bpmMax(int bpmMax) {
        return mergeQ("bpm_max", String.valueOf(bpmMax));
    }

    /**
     * Adds double quotes to string.
     *
     * @param string original string.
     * @return double-quoted string.
     */
    private String str(String string) {
        return "\"" + string + "\"";
    }

    /**
     * Merges Q parameter.
     *
     * @param key q key.
     * @param val q value.
     * @return current instance.
     */
    private AdvancedSearchRequest<T> mergeQ(String key, String val) {
        String newQ = key + ":" + val;
        String existingQ = this.urlBuilder.build().queryParameter("q");
        if (existingQ != null && !existingQ.isEmpty()) {
            newQ = existingQ + " " + newQ;
        }
        this.urlBuilder.setQueryParameter("q", newQ);
        return this;
    }
}
