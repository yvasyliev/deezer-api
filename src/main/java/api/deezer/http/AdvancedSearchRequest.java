package api.deezer.http;

/**
 * Executes Deezer API advanced search request.
 *
 * @param <Answer> response POJO type.
 */
public class AdvancedSearchRequest<Answer> extends SearchRequest<Answer> {
    public AdvancedSearchRequest(String url, Class<Answer> answerClass) {
        super(url, answerClass);
    }

    @Override
    public AdvancedSearchRequest<Answer> limit(int limit) {
        return (AdvancedSearchRequest<Answer>) super.limit(limit);
    }

    @Override
    public AdvancedSearchRequest<Answer> index(int index) {
        return (AdvancedSearchRequest<Answer>) super.index(index);
    }

    /**
     * Adds <b>artist</b> parameter.
     *
     * @param artist artist name.
     * @return current instance.
     */
    public AdvancedSearchRequest<Answer> artist(String artist) {
        return mergeQ("artist", str(artist));
    }

    /**
     * Adds <b>album</b> parameter.
     *
     * @param album album name.
     * @return current instance.
     */
    public AdvancedSearchRequest<Answer> album(String album) {
        return mergeQ("album", str(album));
    }

    /**
     * Adds <b>track</b> parameter.
     *
     * @param track track name.
     * @return current instance.
     */
    public AdvancedSearchRequest<Answer> track(String track) {
        return mergeQ("track", str(track));
    }

    /**
     * Adds <b>label</b> parameter.
     *
     * @param label label.
     * @return current instance.
     */
    public AdvancedSearchRequest<Answer> label(String label) {
        return mergeQ("label", str(label));
    }

    /**
     * Adds <b>dur_min</b> parameter.
     *
     * @param durMin minimum duration.
     * @return current instance.
     */
    public AdvancedSearchRequest<Answer> durMin(int durMin) {
        return mergeQ("dur_min", String.valueOf(durMin));
    }

    /**
     * Adds <b>dur_max</b> parameter.
     *
     * @param durMax maximum duration.
     * @return current instance.
     */
    public AdvancedSearchRequest<Answer> durMax(int durMax) {
        return mergeQ("dur_max", String.valueOf(durMax));
    }

    /**
     * Adds <b>bpm_min</b> parameter.
     *
     * @param bpmMin minimum BPM.
     * @return current instance.
     */
    public AdvancedSearchRequest<Answer> bpmMin(int bpmMin) {
        return mergeQ("bpm_min", String.valueOf(bpmMin));
    }

    /**
     * Adds <b>bpm_max</b> parameter.
     *
     * @param bpmMax maximum BPM.
     * @return current instance.
     */
    public AdvancedSearchRequest<Answer> bpmMax(int bpmMax) {
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
    private AdvancedSearchRequest<Answer> mergeQ(String key, String val) {
        String newQ = key + ":" + val;
        String existingQ = this.urlBuilder.build().queryParameter("q");
        if (existingQ != null && !existingQ.isEmpty()) {
            newQ = existingQ + " " + newQ;
        }
        this.urlBuilder.setQueryParameter("q", newQ);
        return this;
    }
}
