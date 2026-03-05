## Functional Requiments 
###  Overview
* R The System must allow a user to view portfolio without logging in.
* R The system must log every project view with a timestamp to the PostgreSQL database.
* R The Java server must intercept request to a Project and serve the content within the portfolio frame.

### Portfolio Frontend (React)  
* **FR-001**: The system must display a responsive landing page with sections for "About me", "Experience", and "Projects".  
* **FR-002**: The "Projects" section must fetch the list of available projects dynamically from the java backend (not hardcoded).
### API Backend (Java)
* **FR-004**: The backend must expose an endpoint (e.g. POST/api/analytics/views) to accept engagment data from the frontend.
* **FR-005**: The backend must expose an endpoint (e.g. GET/api/projects) to showcase the list of projects and their URLs to the frontend.
## Analystics & Database
* **FR-006**: The system must verify if a project exists before recording an analytic event. 
* **FR-007**: Event logic: Every time a user launches a project demo, the frontend must trigger a database write to the analystics table containing:
    ID, ProjectId (identify which project a viewer clicked on), Timestamp. 
* **FR-009**: The system must provide a secured API endpoint (api/admin/stats) that returns aggregated view counts for owner (myself). 

## Non-Functional Requirements
### Security
* **NFR-001**: Backend must be configured to only accept API calls from the React frontend domain.

### Performance
* **NFR-002**: Concurrency: The database must support simultanceous writes if mltiple users view projects at the same time.
* **NFR-003**: If the Java backend is down, the frontend must fail silently (logging the error to the console) but must load a customer error page to users. 
