import React, { useState } from 'react';

const IframeViewer = ({ src, title = 'Viewer', width = '100%', height = '600px' }) => {
    const [isLoading, setIsLoading] = useState(true);
    const [error, setError] = useState(null);

    const handleLoad = () => {
        setIsLoading(false);
    };

    const handleError = () => {
        setError('Failed to load iframe content');
        setIsLoading(false);
    };

    return (
        <div className="iframe-viewer">
            {isLoading && <p>Loading...</p>}
            {error && <p style={{ color: 'red' }}>{error}</p>}
            <iframe
                src={src}
                title={title}
                width={width}
                height={height}
                onLoad={handleLoad}
                onError={handleError}
                style={{ border: 'none' }}
            />
        </div>
    );
};

export default IframeViewer;