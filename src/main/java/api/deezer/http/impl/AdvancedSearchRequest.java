package api.deezer.http.impl;

/**
 * Executes Deezer API advanced search request.
 *
 * @param <Response> response POJO type.
 */
public class AdvancedSearchRequest<Response> extends SearchRequest<Response> {
    public AdvancedSearchRequest(String url, Class<Response> responseClass) {
        super(url, responseClass);
    }

    @Override
    public AdvancedSearchRequest<Response> limit(int limit) {
        return (AdvancedSearchRequest<Response>) super.limit(limit);
    }

    @Override
    public AdvancedSearchRequest<Response> index(int index) {
        return (AdvancedSearchRequest<Response>) super.index(index);
    }

    /**
     * Adds <b>artist</b> parameter.
     *
     * @param artist artist name.
     * @return current instance.
     */
    public AdvancedSearchRequest<Response> artist(String artist) {
        return mergeQ("artist", str(artist));
    }

    /**
     * Adds <b>album</b> parameter.
     *
     * @param album album name.
     * @return current instance.
     */
    public AdvancedSearchRequest<Response> album(String album) {
        return mergeQ("album", str(album));
    }

    /**
     * Adds <b>track</b> parameter.
     *
     * @param track track name.
     * @return current instance.
     */
    public AdvancedSearchRequest<Response> track(String track) {
        return mergeQ("track", str(track));
    }

    /**
     * Adds <b>label</b> parameter.
     *
     * @param label label.
     * @return current instance.
     */
    public AdvancedSearchRequest<Response> label(String label) {
        return mergeQ("label", str(label));
    }

    /**
     * Adds <b>dur_min</b> parameter.
     *
     * @param durMin minimum duration.
     * @return current instance.
     */
    public AdvancedSearchRequest<Response> durMin(int durMin) {
        return mergeQ("dur_min", String.valueOf(durMin));
    }

    /**
     * Adds <b>dur_max</b> parameter.
     *
     * @param durMax maximum duration.
     * @return current instance.
     */
    public AdvancedSearchRequest<Response> durMax(int durMax) {
        return mergeQ("dur_max", String.valueOf(durMax));
    }

    /**
     * Adds <b>bpm_min</b> parameter.
     *
     * @param bpmMin minimum BPM.
     * @return current instance.
     */
    public AdvancedSearchRequest<Response> bpmMin(int bpmMin) {
        return mergeQ("bpm_min", String.valueOf(bpmMin));
    }

    /**
     * Adds <b>bpm_max</b> parameter.
     *
     * @param bpmMax maximum BPM.
     * @return current instance.
     */
    public AdvancedSearchRequest<Response> bpmMax(int bpmMax) {
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
    private AdvancedSearchRequest<Response> mergeQ(String key, String val) {
        this.getParams().merge("q", key + ":" + val, (existingQ, newQ) -> existingQ + " " + newQ);
        return this;
    }
}
