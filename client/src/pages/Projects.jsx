import React, { useState, useEffect } from 'react';

function ProductCard() {
  const [projects, setProjects] = useState([]);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    // 1. Send HTTP request to backend
    fetch('http://localhost:8080/api/projects')
        .then((response) => {
            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }
            return response.json();
        })
        .then((data) => {
            setProjects(data);                // 2. Save data into React state
            setLoading(false);
        })
      .catch((error) => {
        console.error('Error fetching projects:', error);
        setError(error.message);
        setLoading(false);
      });
    }, []);// Empty array ensures this runs once when the component mounts

  if (loading) return <p>Loading item...</p>;

  return (
    <div style={{ border: '1px solid #ccc', padding: '16px', borderRadius: '8px' }}>
      <ul>
        {projects.map(project => 
            <li key={project.id}>{project.project_name}</li>
        )}
      </ul>
    </div>
  );
}

export default ProductCard;